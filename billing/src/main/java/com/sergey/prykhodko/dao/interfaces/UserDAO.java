package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.users.Client;
import com.sergey.prykhodko.users.User;
import com.sergey.prykhodko.users.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.List;

public abstract class UserDAO implements DAO {
    protected Connection connection;
    protected Statement statement;
    protected String addQuery;

    public abstract void addUser(User user) throws SQLException;


    public User getUserByLogin(String login) throws SQLException{ //TODO change to each implementation method
        User user = null;
        statement = connection.createStatement();
        addQuery = addValue(addQuery, login);
        ResultSet resultSet = statement.executeQuery(addQuery);

        if (resultSet.next()) {
            user = new Client(resultSet.getString(1), resultSet.getString(2));//problem here I'm creating always client
        }

        return user;
    }

    public abstract User getUserByID(int id) throws SQLException;

    public abstract void updateUser(Client client) throws SQLException;

    private String addValue(String query, String entry) {
        Formatter formatter = new Formatter();
        formatter.format(query, entry);
        return formatter.toString();
    }

    public abstract List<? extends User> getAllUsers(UserRole role) throws SQLException;

}
