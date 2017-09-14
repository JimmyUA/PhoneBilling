package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.model.users.User;
import com.sergey.prykhodko.model.users.UserRole;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
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
    public Set<String> getLogins() throws SQLException {
        return null;
    }
}
