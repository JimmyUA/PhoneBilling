package com.sergey.prykhodko.dao.implementations.mySQL;

import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;
import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.model.users.ClientBuilder;
import com.sergey.prykhodko.model.users.User;
import com.sergey.prykhodko.model.users.UserRole;
import org.apache.log4j.Logger;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

import static com.sergey.prykhodko.model.users.UserRole.CLIENT;
import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;

public class ClientMySqlDAO extends UserDAO {
    private static Logger logger = Logger.getLogger(getCurrentClassName());
    private String getClientQuery = "SELECT  * FROM clients " +
            "INNER JOIN tariffs ON clients.id_tariff=tariffs.id_tariff " +
            "WHERE login = '%s'";

    public ClientMySqlDAO() throws NamingException, SQLException {
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
    public User getUserByID(int id) throws SQLException {
        return getClientByID(id);
    }

    @Override
    public void updateUser(Client client) throws SQLException {
        final String query = "UPDATE clients SET password='" + client.getPassword() + "', email='"
                + client.getEmail() + "', status=" + intStatus(client.isActive()) + " WHERE id_client=" + client.getId();

        statement.execute(query);
    }

    @Override
    public List<Client> getAllUsers(UserRole role) throws SQLException {
        List<Client> clients = new ArrayList<>();
        final String getAllClientsQuery = "SELECT * FROM clients";
        try (ResultSet resultSet = statement.executeQuery(getAllClientsQuery)) {
            if (role == CLIENT) {
                Client client;
                while (resultSet.next()) {
                    client = buildClientFromDB(resultSet);
                    clients.add(client);
                }
                return clients;
            }
            return Collections.emptyList();
        }
    }

    private Client buildClientFromDB(ResultSet resultSet) throws SQLException {
        int id = Integer.parseInt(resultSet.getString(1));
        String login = resultSet.getString(2);
        String password = resultSet.getString(3);
        String email = resultSet.getString(4);
        String fullName = resultSet.getString(6);
        boolean isActive = getStatusFromInt(Integer.parseInt(resultSet.getString(7)));
        TariffPlan tariffPlan = null; //Todo get tariff using tariffDAO

        Client client = new ClientBuilder()
                .setId(id)
                .setLogin(login)
                .setPassword(password)
                .setFullName(fullName)
                .setEmail(email)
                .setActive(isActive)
                .build();
        return client;
    }

    private boolean getStatusFromInt(int i) {
        return i == 0;
    }

    public Set<String> getLogins() throws SQLException {
        Set<String> logins = new HashSet<>();
        final String loginsQuery = "SELECT login FROM clients";

        try(ResultSet result = statement.executeQuery(loginsQuery)) {
            while (result.next()) {
                logins.add(result.getString(1));
            }
            return logins;
        }
    }

    private Client getClientByID(int ID) throws SQLException {
        Client client = null;
        statement = connection.createStatement();
        String query = "SELECT * FROM clients WHERE id_client=" + ID;
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            client = buildClientFromDB(resultSet);
        }

        return client;
    }

    private int intStatus(boolean status) {
        if (status) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public List<Client> getAllUsersPortion(int portion, int pageNumber) throws SQLException {
        List<Client> clients = new ArrayList<>();
        int start = (portion * pageNumber) - portion;
        final String getAllClientsQuery = "SELECT * FROM clients ORDER BY login LIMIT "
                + start + ", " + portion;

        try(ResultSet resultSet = statement.executeQuery(getAllClientsQuery)) {
            Client client;
            while (resultSet.next()) {
                client = buildClientFromDB(resultSet);
                clients.add(client);
            }
            return clients;
        }
    }

    @Override
    public int getTotalClientsAmount() throws SQLException {
        final String getTotalClientsAmountQuery = "SELECT COUNT(*) FROM clients";

        try(ResultSet resultSet = statement.executeQuery(getTotalClientsAmountQuery)) {
            resultSet.next();
            int clientsAmount = Integer.parseInt(resultSet.getString(1));
            return clientsAmount;
        }
    }
}
