package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.users.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.concurrent.Future;

public class ClientDAOMySQL implements DAO {
    Connection connection;

    public ClientDAOMySQL() throws NamingException, SQLException {
        InitialContext initContext= new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/dbconnect");
        connection = ds.getConnection();
    }

    @Override
    public void storeUser(User user) {

    }

    @Override
    public User getUser() {
        return null;
    }

}
