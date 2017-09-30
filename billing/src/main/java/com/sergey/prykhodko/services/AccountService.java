package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.dao.interfaces.AccountDAO;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;

import javax.naming.NamingException;
import java.sql.SQLException;

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
}
