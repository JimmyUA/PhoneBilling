package com.sergey.prykhodko.services.commands;

import com.sergey.prykhodko.connection.TransactionManager;
import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.exceptions.NotEnoughMoneyException;
import com.sergey.prykhodko.model.account.Invoice;
import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.services.AccountService;
import com.sergey.prykhodko.services.InvoiceService;
import com.sergey.prykhodko.services.PaymentService;
import com.sergey.prykhodko.services.UsersService;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.sergey.prykhodko.dao.factories.FactoryType.*;
import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

public class PayForAllCommand implements Command{

    private static Logger logger = Logger.getLogger(getCurrentClassName());

    private static final String NOT_ENOUGH_MONEY_MESSAGE = "You have not enough balance, please pop-up it";

    private HttpServletRequest request;
    private Client client;
    private List<Invoice> unpaidInvoices;
    private Connection transactionConnection;

    public PayForAllCommand(HttpServletRequest request) throws SQLException, NamingException {
        this.request = request;
        client = (Client) request.getSession().getAttribute("user");
        unpaidInvoices = new UsersService().getUnpaidInvoices(client, MySQL);
    }

    @Override
    public void execute() throws SQLException, NamingException {
        TransactionManager manager = new TransactionManager();
        transactionConnection = manager.getTransactionConnection();

        try {
            executePayment();
        } catch (NotEnoughMoneyException e){
            logger.info(e);
            throw new NotEnoughMoneyException(NOT_ENOUGH_MONEY_MESSAGE);
        }
        catch (Throwable t){
            logger.error(t);
            t.printStackTrace();
            manager.rollBack();
        }
        manager.commit();
        manager.returnConnection();
    }

    private void executePayment() throws SQLException, NamingException {
        BigDecimal totalSum = getTotalSum();
        client.pay(totalSum);
        new AccountService().updateAccount(client.getAccount(), MySQL, transactionConnection);
        generatePayments();
        updateInvoicesInDB();
    }

    private void generatePayments() throws SQLException, NamingException {
        new PaymentService().generatePayments(unpaidInvoices, MySQL, transactionConnection);
        setPaidInvoices();
    }

    private void updateInvoicesInDB() throws SQLException, NamingException {
            InvoiceService invoiceService = new InvoiceService();
        for (Invoice invoice : unpaidInvoices
             ) {
            invoiceService.updateInvoices(invoice, MySQL, transactionConnection);
        }
    }

    private void setPaidInvoices() {
        for (Invoice invoice : unpaidInvoices
             ) {
            invoice.setPaid(true);
        }
    }

    private BigDecimal getTotalSum(){
        BigDecimal total = BigDecimal.ZERO;
        for (Invoice invoice : unpaidInvoices
             ) {
            total = total.add(invoice.getAmount());
        }

        return total;
    }
}
