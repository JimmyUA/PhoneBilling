package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.model.tariffplans.TariffPlan;
import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.model.users.ClientBuilder;
import com.sergey.prykhodko.model.users.User;
import com.sergey.prykhodko.model.users.UserRole;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.List;
import java.util.Set;

import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;

public abstract class UserDAO implements DAO {
    protected Connection connection;
    protected String addQuery;

    private static Logger logger = Logger.getLogger(getCurrentClassName());

    public abstract void addUser(User user) throws SQLException;


    public abstract User getUserByLogin(String login) throws SQLException;

    public abstract User getUserByID(int id) throws SQLException;

    public abstract void updateUser(User user) throws SQLException;

    protected String addValue(String query, String entry) {
        Formatter formatter = new Formatter();
        formatter.format(query, entry);
        return formatter.toString();
    }

    public abstract List<? extends User> getAllUsers(UserRole role) throws SQLException;

    public abstract List<? extends User> getAllUsersPortion(int portion, int startFrom) throws SQLException;

    public abstract int getTotalUsersAmount() throws SQLException;

    protected void closeConnection() throws SQLException {
        if (connection != null){
            connection.close();
        }
    }


    public abstract Set<String> getLogins() throws SQLException;
}
