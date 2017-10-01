package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.dao.interfaces.AccountDAO;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.InvoiceDAO;
import com.sergey.prykhodko.model.account.Account;
import com.sergey.prykhodko.model.account.Invoice;
import com.sergey.prykhodko.model.account.InvoiceBuilder;
import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.model.users.User;
import com.sergey.prykhodko.util.Accounts;
import com.sergey.prykhodko.util.Invoices;

import javax.naming.NamingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AccountService implements Service{

    public Integer getLastId(FactoryType factoryType) throws SQLException, NamingException {
        AccountDAO accountDAO = getAccountDAO(factoryType);
        return accountDAO.getLastID();
    }

    private AccountDAO getAccountDAO(FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(factoryType);
        return daoFactory.getAccountDAO();
    }

    public void createAccountForNewClient(Integer accountId, FactoryType factoryType) throws SQLException, NamingException {
        AccountDAO accountDAO = getAccountDAO(factoryType);
        accountDAO.add(accountId);
    }

    public Account getNewAccount() {
       Account account = new Account();
       account.setAccountId(Accounts.generateId());
       account.setBalance(BigDecimal.valueOf(0.000));
       return account;
    }

    public void updateAccount(Account account, FactoryType factoryType) throws SQLException, NamingException {
        AccountDAO accountDAO = getAccountDAO(factoryType);
        accountDAO.update(account);
    }

    public void generateInvoices(List<? extends User> clients, FactoryType factoryType) throws SQLException, NamingException {
        for (User user : clients
             ) {
            Client client = (Client) user;
            List<Invoice> clientInvoices = client.getAccount().getInvoices();
            final LocalDate dueDate = LocalDate.now().plusMonths(1L);
            final Integer accountId = client.getAccount().getId();
            final String invoiceNumber = Invoices.generateNumber(client.getAccount());
            final BigDecimal chargePerMonth = client.getTariffPlan().getChargePerMonth();
            Invoice newInvoice = new InvoiceBuilder()
                    .setNumber(invoiceNumber)
                    .setAmount(chargePerMonth)
                    .setDueDate(dueDate)
                    .setAccountId(accountId)
                    .setPaid(false)
                    .build();
            InvoiceDAO invoiceDAO = getInvoiceDAO(factoryType);
            invoiceDAO.add(newInvoice);
            clientInvoices.add(newInvoice);
        }
    }

    private InvoiceDAO getInvoiceDAO(FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(factoryType);
        return daoFactory.getInvoiceDAO();
    }
}
