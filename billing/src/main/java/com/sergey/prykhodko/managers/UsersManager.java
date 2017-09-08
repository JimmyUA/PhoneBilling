package com.sergey.prykhodko.users;

import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.UserDAO;
import org.apache.log4j.Logger;
import servlets.LoginServlet;

import javax.naming.NamingException;
import java.sql.SQLException;

public class UsersManager {


    public User getUserFromDB(String login, FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory factory = DAOFactory.getDAOFactory(factoryType);
        UserDAO userDAO = factory.getUserDAO(UserRole.CLIENT);
        User user = userDAO.getUser(login);
        if (userIsFound(user)){
            user.setRole(UserRole.CLIENT);
            return user;
        }
        else {
            userDAO = factory.getUserDAO(UserRole.ADMIN);
            user = userDAO.getUser(login);
            if (userIsFound(user)) {
                user.setRole(UserRole.ADMIN);
            }
            return user;
        }
    }

    private boolean userIsFound(User user) {
        return user != null;
    }
}
