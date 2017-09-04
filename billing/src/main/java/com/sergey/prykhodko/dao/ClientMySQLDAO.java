package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.dao.interfaces.DAO;
import com.sergey.prykhodko.users.User;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class ClientDAOMySQL implements DAO {
    Connection connection;

    public ClientDAOMySQL() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup("java:comp/env");

        DataSource ds = (DataSource) context.lookup("jdbc/billing");
        connection = ds.getConnection();
    }

    @Override
    public void storeUser(User user) {
        final String storeNewClientQuery = "INSERT INTO clients (username, password, email, name, status, id_tariff)" +
                " VALUES(" + /*user.getLogin() + */")";

    }

    @Override
    public User getUser() {
        return null;
    }

}
