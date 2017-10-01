package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.InvoiceDAO;
import com.sergey.prykhodko.model.account.Account;
import com.sergey.prykhodko.model.account.Invoice;
import com.sergey.prykhodko.model.users.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class InvoiceService implements Service{

    public Integer getLastDigitOfInvoice(Account account, FactoryType factoryType) throws SQLException, NamingException {
        InvoiceDAO invoiceDAO = getInvoiceDAO(factoryType);
        int max = 0;
        List<String> invoiceNumbers = invoiceDAO.getInvoiceNumbersByAccountId(account.getId());
        for (String number : invoiceNumbers
             ) {
            int lastDigit = Integer.parseInt(number.substring(7));
            if (lastDigit > max){
                max = lastDigit;
            }
        }
        return max;
    }

    private InvoiceDAO getInvoiceDAO(FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(factoryType);
        return daoFactory.getInvoiceDAO();
    }

    public void generateInvoices(FactoryType factoryType) throws SQLException, NamingException {
        List<? extends User> clients = new UsersService().getAllClients(factoryType);
        new AccountService().generateInvoices(clients, factoryType);

    }
}
