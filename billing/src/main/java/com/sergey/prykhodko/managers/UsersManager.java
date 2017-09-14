package com.sergey.prykhodko.managers;

import com.sergey.prykhodko.dao.ClientMySQLDAO;
import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.model.users.Admin;
import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.model.users.User;
import com.sergey.prykhodko.model.users.UserRole;


import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

import static com.sergey.prykhodko.model.users.UserRole.*;

public class UsersManager {


    public User getUserByLogin(String login, FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory factory = DAOFactory.getDAOFactory(factoryType);
        UserDAO userDAO = factory.getUserDAO(CLIENT);
        User user = userDAO.getUserByLogin(login);
        if (isUserFound(user)) {
            user.setRole(CLIENT);
            return user;
        } else {
            userDAO = factory.getUserDAO(ADMIN);
            user = userDAO.getUserByLogin(login);
            if (isUserFound(user)) {
                user.setRole(ADMIN);
            }
            return user;
        }
    }

    public User getUserByID(int id, FactoryType factoryType) throws SQLException, NamingException {
        UserDAO userDAO = getUserDAO(CLIENT, factoryType);
        User user = userDAO.getUserByID(id);
        if (isUserFound(user)) {
            user.setRole(CLIENT);
            return user;
        } else {
            userDAO = getUserDAO(ADMIN, factoryType);
            user = userDAO.getUserByID(id);
            if (isUserFound(user)) {
                user.setRole(ADMIN);
            }
            return user;
        }
    }

    private boolean isUserFound(User user) {
        return user != null;
    }

    public void addUserToDB(User user, FactoryType factoryType) throws SQLException, NamingException {
        switch (user.getRole()) {
            case CLIENT:
                addClientToDB(user, factoryType);
                break;
            case ADMIN:
                addAdminToDB(user, factoryType);
                break;
        }
    }

    private void addAdminToDB(User user, FactoryType factoryType) {
        //TODO implement if needed
    }

    private void addClientToDB(User user, FactoryType factoryType) throws SQLException, NamingException {
        Client client = (Client) user;
        UserDAO userDAO = getUserDAO(CLIENT, factoryType);
        userDAO.addUser(client);
    }

    public List<Client> getAllClients(FactoryType factoryType) throws SQLException, NamingException {
        List<Client> clients;
        UserDAO userDAO = getUserDAO(CLIENT, factoryType);
        ClientMySQLDAO clientDAO = (ClientMySQLDAO) userDAO;
        clients = clientDAO.getAllUsers(CLIENT);
        return clients;
    }

    public void changeStatus(){
        //TODO implement
    }

    private UserDAO getUserDAO(UserRole role, FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory factory = DAOFactory.getDAOFactory(factoryType);
        return factory.getUserDAO(role);
    }

    public void updateUser(User user, FactoryType factoryType) throws SQLException, NamingException {
        if (user.getRole() == CLIENT){
            updateClient((Client) user, factoryType);
        }
        else if(user.getRole() == ADMIN){
            updateAdmin((Admin) user, factoryType);
        }
    }

    private void updateClient(Client client, FactoryType factoryType) throws SQLException, NamingException {
        UserDAO userDAO = getUserDAO(CLIENT, factoryType);
        userDAO.updateUser(client);
    }

    private void updateAdmin(Admin admin, FactoryType factoryType) {
        throw new UnsupportedOperationException();
    }
}
