package com.sergey.prykhodko.dao.implementations.mySQL;

import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;
import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.model.users.ClientBuilder;
import com.sergey.prykhodko.model.users.User;
import com.sergey.prykhodko.model.users.UserRole;
import org.apache.log4j.Logger;


import javax.naming.NamingException;
import java.sql.*;
import java.util.*;

import static com.sergey.prykhodko.connection.pool.ConnectionPool.getConnection;
import static com.sergey.prykhodko.model.users.UserRole.CLIENT;
import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

public class ClientMySqlDAO extends UserDAO {
    private static final String ADD_CLIENT =
            "INSERT INTO clients (login, password, email, name, status, id_tariff, id_account) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_CLIENT_BY_ID = "SELECT * FROM clients WHERE id_client=?";
    private static final String UPDATE_CLIENT =
            "UPDATE clients SET password=?, email=?, status=?, id_tariff=? WHERE id_client=?";
    private static final String GET_ALL_CLIENTS = "SELECT * FROM clients";
    private static final String GET_ALL_LOGINS = "SELECT login FROM clients";
    private static final String GET_CLIENTS_PORTION = "SELECT * FROM clients ORDER BY login LIMIT ?, ?";
    private static final String GET_CLIENTS_AMOUNT = "SELECT COUNT(*) FROM clients";
    private static final String GET_CLIENT_BY_LOGIN = "SELECT  * FROM clients " +
                                    "INNER JOIN tariffs ON clients.id_tariff=tariffs.id_tariff " +
                                    "WHERE login=?";

    private static final String LOGIN_LABEL = "login";
    private static final String PASSWORD_LABEL = "login";

    private static Logger logger = Logger.getLogger(getCurrentClassName());

    public ClientMySqlDAO() throws NamingException, SQLException {
       connection = getConnection();
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        return getClientByLogin(login);
    }

    private User getClientByLogin(String login) throws SQLException {
        Client client = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_CLIENT_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                client = new ClientBuilder()
                        .setLogin(resultSet.getString(2))
                        .setPassword(resultSet.getString(3))
                        .setEmail(resultSet.getString(4))
                        .setFullName(resultSet.getString(6))
                        .setActive(resultSet.getBoolean(7))
                        .setId(resultSet.getInt(1))
                        .setTariffPlanId(resultSet.getInt(8))
                        .setAccountId(resultSet.getInt(9))
                        .build();

            }

            return client;
        } finally {
            closeConnection();
        }
    }

    @Override
    public void addUser(User user) throws SQLException {
        Client client = (Client) user;

        addClient(client);
    }

    private void addClient(Client client) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(ADD_CLIENT)) {
            statement.setString(1, client.getLogin());
            statement.setString(2, client.getPassword());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getFullName());
            statement.setBoolean(5, client.isActive());
            statement.setInt(6, client.getTariffPlanId());
            statement.setInt(7, client.getAccountId());
            statement.execute();
        } finally {
            closeConnection();
        }
    }

    @Override
    public User getUserByID(int ID) throws SQLException {
        return getClientByID(ID);
    }

    private Client getClientByID(int ID) throws SQLException {
        Client client = null;

        try(PreparedStatement statement = connection.prepareStatement(GET_CLIENT_BY_ID)) {
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                client = buildClientFromDB(resultSet);
            }
        } finally {
            closeConnection();
        }
        return client;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        Client client = (Client) user;

        updateClient(client);
    }

    private void updateClient(Client client) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT)){
            statement.setString(1, client.getPassword());
            statement.setString(2, client.getEmail());
            statement.setBoolean(3, client.isActive());
            statement.setInt(4, client.getTariffPlanId());
            statement.setInt(5, client.getId());
            statement.execute();
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Client> getAllUsers(UserRole role) throws SQLException {

        return getClients(role);
    }

    private List<Client> getClients(UserRole role) throws SQLException {
        List<Client> clients = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_CLIENTS);
            if (role == CLIENT) {
                Client client;
                while (resultSet.next()) {
                    client = buildClientFromDB(resultSet);
                    clients.add(client);
                }
                return clients;
            }
            return Collections.emptyList();
        } finally {
            closeConnection();
        }
    }

    private Client buildClientFromDB(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String login = resultSet.getString(2);
        String password = resultSet.getString(3);
        String email = resultSet.getString(4);
        String fullName = resultSet.getString(6);
        boolean isActive = resultSet.getBoolean(7);
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

    public Set<String> getLogins() throws SQLException {
        Set<String> logins = new HashSet<>();

        try(Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(GET_ALL_LOGINS);
            while (result.next()) {
                logins.add(result.getString(1));
            }
            return logins;
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Client> getAllUsersPortion(int portion, int pageNumber) throws SQLException {
        List<Client> clients = new ArrayList<>();
        int start = (portion * pageNumber) - portion;

        try(PreparedStatement statement = connection.prepareStatement(GET_CLIENTS_PORTION)) {
            statement.setInt(1, start);
            statement.setInt(2, portion);
            ResultSet resultSet = statement.executeQuery();
            Client client;
            while (resultSet.next()) {
                client = buildClientFromDB(resultSet);
                clients.add(client);
            }
            return clients;
        } finally {
            closeConnection();
        }
    }

    @Override
    public int getTotalUsersAmount() throws SQLException {

        return getClientsAmount();
    }

    private int getClientsAmount() throws SQLException {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_CLIENTS_AMOUNT);
            resultSet.next();
            int clientsAmount = Integer.parseInt(resultSet.getString(1));
            return clientsAmount;
        } finally {
            closeConnection();
        }
    }
}
