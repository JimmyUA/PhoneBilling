package com.sergey.prykhodko.dao.implementations.mySQL;

import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.model.users.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class AdminMySqlDAO extends UserDAO {
    @Override
    public void updateUser(Client client) {

    }

    private String addAdminQuery = "SELECT  login, password FROM admins WHERE login = '%s'";


    public AdminMySqlDAO() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup("java:comp/env");

        DataSource ds = (DataSource) context.lookup("jdbc/billing");
        connection = ds.getConnection();
        addQuery = addAdminQuery;
    }


    @Override
    public void addUser(User user) throws SQLException {

    }

    @Override
    public User getUserByID(int id) throws SQLException {
        return null; //TODO implement if needed
    }

    @Override
    public List<? extends User> getAllUsers(UserRole role) throws SQLException {
        return null;
    }

    @Override
    public List<Client> getAllUsersPortion(int portion, int startFrom) throws SQLException {
        return null;
    }

    @Override
    public Set<String> getLogins() {
        return null;
    }

    @Override
    public int getTotalClientsAmount() {
        return 0;
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        Admin admin = null;
        statement = connection.createStatement();
        addQuery = addValue(addQuery, login);
        ResultSet resultSet = statement.executeQuery(addQuery);

        if (resultSet.next()) {
            admin = new Admin(resultSet.getString(1), resultSet.getString(2));
        }

        return admin;
    }
}
