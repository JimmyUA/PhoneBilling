package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.model.account.Account;

import java.sql.Connection;
import java.sql.SQLException;

public interface AccountDAO{
    Integer getLastID() throws SQLException;

    void add(Integer accountId) throws SQLException;

    void update(Account account)throws SQLException;

    void setConnection(Connection transactionConnection) throws SQLException;
}

