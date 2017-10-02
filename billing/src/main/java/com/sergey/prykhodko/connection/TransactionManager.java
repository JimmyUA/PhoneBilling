package com.sergey.prykhodko.connection;

import com.sergey.prykhodko.connection.pool.ConnectionPool;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private Connection transactionConnection;

    public TransactionManager() throws SQLException, NamingException {
        transactionConnection = ConnectionPool.getTransactionConnection();
    }

    public Connection getTransactionConnection(){
        return transactionConnection;
    }

    public void commit() throws SQLException {
        transactionConnection.commit();
    }

    public void rollBack() throws SQLException {
        transactionConnection.rollback();
    }

    public void returnConnection() throws SQLException {
        transactionConnection.setAutoCommit(true);
        transactionConnection.close();
    }
}
