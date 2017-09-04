package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.users.Client;
import com.sergey.prykhodko.users.User;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class ClientMySQLDAO implements UserDAO {
    private Connection connection;

    public ClientMySQLDAO() throws NamingException, SQLException {
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
    public User getUser(String login) throws SQLException {
        User client = null;
        final String getClientQuery = "SELECT  username, password FROM clients WHERE username = '" + login + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getClientQuery);

        if (resultSet.next()) {
            client = new Client(resultSet.getString(1), resultSet.getString(2));
        }

        return client;
    }

}
