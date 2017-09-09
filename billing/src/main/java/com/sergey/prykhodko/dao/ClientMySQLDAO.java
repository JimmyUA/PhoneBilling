package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.users.Client;
import com.sergey.prykhodko.users.User;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientMySQLDAO extends UserDAO {
    private String getClientQuery = "SELECT  login, password FROM clients WHERE login = '%s'";

    public ClientMySQLDAO() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup("java:comp/env");


        DataSource ds = (DataSource) context.lookup("jdbc/billing");
        connection = ds.getConnection();
        statement = connection.createStatement();
        addQuery = getClientQuery;
    }
    @Override
    public void addUser(User user) throws SQLException {
        Client client = (Client) user;
        final String addClientQuery = "INSERT INTO clients (login, password, email, name, status, id_tariff)" +
                " VALUES('" + client.getLogin() + "', '" + client.getPassword() + "', '" + client.getEmail() +
                 "', '" + client.getFullName() + "', " + intStatus(client.isActive()) + ", 1)";

        statement.execute(addClientQuery);
    }

    @Override
    public Set<String> getLogins() throws SQLException {
        Set<String> logins = new HashSet<>();
        final String loginsQuery = "SELECT login FROM clients";

        ResultSet result = statement.executeQuery(loginsQuery);
        while (result.next()){
            logins.add(result.getString(1));
        }
        return logins;
    }

    private int intStatus(boolean status){
        if (status){
            return 0;
        }
        else {
            return 1;
        }
    }
}
