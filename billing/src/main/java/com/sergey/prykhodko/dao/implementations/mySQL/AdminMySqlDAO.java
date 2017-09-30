package com.sergey.prykhodko.dao.implementations.mySQL;

import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.model.users.*;
import org.apache.log4j.Logger;


import javax.naming.NamingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import static com.sergey.prykhodko.connection.pool.ConnectionPool.getConnection;
import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

public class AdminMySqlDAO extends UserDAO {
    private static final String GET_ADMIN_BY_LOGIN = "SELECT  login, password FROM admins WHERE login = ?";

    private static Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    public void updateUser(User user) {

    }


    public AdminMySqlDAO() throws NamingException, SQLException {
        connection = getConnection();
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
    public int getTotalUsersAmount() {
        return 0;
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        return getAdminByLogin(login);
    }

    private User getAdminByLogin(String login) throws SQLException {
        Admin admin = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_ADMIN_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                admin = new Admin(resultSet.getString(1), resultSet.getString(2));
            }

        }
        return admin;
    }
}
