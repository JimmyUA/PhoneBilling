package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.dao.interfaces.AccountDAO;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.model.account.Account;
import com.sergey.prykhodko.util.Accounts;

import javax.naming.NamingException;
import java.math.BigDecimal;
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
}
