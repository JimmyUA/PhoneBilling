package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.users.Client;
import com.sergey.prykhodko.users.User;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;

public abstract class UserDAO implements DAO {
    protected Connection connection;
    protected String query;

    public void storeUser(User user){}

    public User getUser(String login) throws SQLException{
        User user = null;
        Statement statement = connection.createStatement();
        query = addValue(query, login);
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            user = new Client(resultSet.getString(1), resultSet.getString(2));
        }

        return user;
    }

    private String addValue(String query, String entry) {
        Formatter formatter = new Formatter();
        formatter.format(query, entry);
        return formatter.toString();
    }

}
