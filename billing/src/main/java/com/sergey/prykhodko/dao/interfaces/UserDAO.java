package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.users.Client;
import com.sergey.prykhodko.users.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;

public abstract class UserDAO implements DAO {
    protected Connection connection;
    protected Statement statement;
    protected String addQuery;

    public abstract void addUser(User user) throws SQLException;


    public User getUser(String login) throws SQLException{
        User user = null;
        statement = connection.createStatement();
        addQuery = addValue(addQuery, login);
        ResultSet resultSet = statement.executeQuery(addQuery);

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
