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

import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;

public abstract class UserDAO implements DAO {
    protected Connection connection;
    protected Statement statement;
    protected String addQuery;

    private static Logger logger = Logger.getLogger(getCurrentClassName());

    public abstract void addUser(User user) throws SQLException;


    public User getUserByLogin(String login) throws SQLException{ //TODO change to each implementation method
        Client client = null;
        try {
            statement = connection.createStatement();
            addQuery = addValue(addQuery, login);
            ResultSet resultSet = statement.executeQuery(addQuery);

            if (resultSet.next()) {
                TariffPlan tariffPlan = new TariffPlan(resultSet.getString(10));

                client = new ClientBuilder()
                        .setLogin(resultSet.getString(2))
                        .setPassword(resultSet.getString(3))
                        .setEmail(resultSet.getString(4))
                        .setFullName(resultSet.getString(6))
                        .setActive(resultSet.getBoolean(7))
                        .setId(resultSet.getInt(1))
                        .setTariffPlan(tariffPlan)
                        .build();

            }

            return client;
        } catch (SQLException e){
            logger.error(e);
            return null;
        }
    }

    public abstract User getUserByID(int id) throws SQLException;

    public abstract void updateUser(Client client) throws SQLException;

    protected String addValue(String query, String entry) {
        Formatter formatter = new Formatter();
        formatter.format(query, entry);
        return formatter.toString();
    }

    public abstract List<? extends User> getAllUsers(UserRole role) throws SQLException;

    public abstract List<Client> getAllUsersPortion(int portion, int startFrom) throws SQLException;

    public abstract int getTotalClientsAmount() throws SQLException;

    public void closeConnection() throws SQLException {
        statement.close();
        connection.close();
    }


}
