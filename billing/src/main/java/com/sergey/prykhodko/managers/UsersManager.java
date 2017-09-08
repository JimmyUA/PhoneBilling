package com.sergey.prykhodko.managers;

import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.users.User;
import com.sergey.prykhodko.users.UserRole;


import javax.naming.NamingException;
import java.sql.SQLException;

public class UsersManager {


    public User getUserFromDB(String login, FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory factory = DAOFactory.getDAOFactory(factoryType);
        UserDAO userDAO = factory.getUserDAO(UserRole.CLIENT);
        User user = userDAO.getUser(login);
        if (isUserFound(user)) {
            user.setRole(UserRole.CLIENT);
            return user;
        } else {
            userDAO = factory.getUserDAO(UserRole.ADMIN);
            user = userDAO.getUser(login);
            if (isUserFound(user)) {
                user.setRole(UserRole.ADMIN);
            }
            return user;
        }
    }

    private boolean isUserFound(User user) {
        return user != null;
    }
}
