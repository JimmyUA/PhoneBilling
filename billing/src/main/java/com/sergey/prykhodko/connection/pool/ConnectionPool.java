package com.sergey.prykhodko.connection.pool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    String CONTEXT_PATH = "java:comp/env";
    String DATA_SOURCE_PATH = "jdbc/billing";



    static Connection getConnection() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup(CONTEXT_PATH);


        DataSource dataSource = (DataSource) context.lookup(DATA_SOURCE_PATH);
        Connection connection = dataSource.getConnection();
        return connection;
    }

    static Connection getTransactionConnection() throws SQLException, NamingException {
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup(CONTEXT_PATH);


        DataSource dataSource = (DataSource) context.lookup(DATA_SOURCE_PATH);
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }
}
