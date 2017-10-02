package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.dao.interfaces.*;
import com.sergey.prykhodko.model.account.Account;
import com.sergey.prykhodko.model.account.Invoice;
import com.sergey.prykhodko.model.account.Payment;
import com.sergey.prykhodko.model.services.Service;
import com.sergey.prykhodko.model.users.Admin;
import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.model.users.User;
import com.sergey.prykhodko.model.users.UserRole;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static com.sergey.prykhodko.model.users.UserRole.*;
import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

public class UsersService {
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    public User getUserByLogin(String login, FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory factory = DAOFactory.getDAOFactory(factoryType);
        UserDAO userDAO = factory.getUserDAO(CLIENT);
        User user = userDAO.getUserByLogin(login);
        logger.info(userDAO);
        if (isUserFound(user)) {
            user.setRole(CLIENT);
            Client client = (Client) user;
            setFullTariff(client, factoryType);
            setFullAccount(client, factoryType);
            return client;
        } else {
            userDAO = factory.getUserDAO(ADMIN);
            user = userDAO.getUserByLogin(login);
            if (isUserFound(user)) {
                user.setRole(ADMIN);
            }
            return user;
        }
    }

    private void setFullAccount(Client client, FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(factoryType);
        InvoiceDAO invoiceDAO = daoFactory.getInvoiceDAO();
        List<Invoice> invoices = invoiceDAO.getInvoicesByAccountID(client.getAccount().getId());
        PaymentDAO paymentDAO = daoFactory.getPaymentDAO();
        List<Payment> payments = paymentDAO.getPaymentsByAccountID(client.getAccount().getId());
        Account account = client.getAccount();
        account.setInvoices(invoices);
        account.setPayments(payments);
    }

    private void setFullTariff(Client client, FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(factoryType);
        ServiceDAO serviceDAO = daoFactory.getServiceDAO();
        List<Service> services = serviceDAO.getServicesByTariffPlanID(client.getTariffPlan().getId());
        client.getTariffPlan().setServices(services);
    }

    public User getUserByID(int id, FactoryType factoryType) throws SQLException, NamingException {
        UserDAO userDAO = getUserDAO(CLIENT, factoryType);
        User user = userDAO.getUserByID(id);
        if (isUserFound(user)) {
            user.setRole(CLIENT);
            Client client = (Client) user;
            setFullAccount(client, factoryType);
            setFullTariff(client, factoryType);
            return client;
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
        new AccountService().createAccountForNewClient(client.getAccount().getId(), factoryType);
        UserDAO userDAO = getUserDAO(CLIENT, factoryType);
        userDAO.addUser(client);
    }

    public List<? extends User> getAllClients(FactoryType factoryType) throws SQLException, NamingException {
        List<? extends User> clients;
        UserDAO userDAO = getUserDAO(CLIENT, factoryType);
        clients = userDAO.getAllUsers(CLIENT);
        setFullAccountAndTariff(factoryType, clients);
        return clients;
    }

    private void setFullAccountAndTariff(FactoryType factoryType, List<? extends User> clients) throws SQLException, NamingException {
        for (User user: clients
             ) {
            Client client = (Client) user;
            setFullAccount(client, factoryType);
            setFullTariff(client, factoryType);
        }
    }


    private UserDAO getUserDAO(UserRole role, FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory factory = DAOFactory.getDAOFactory(factoryType);
        return factory.getUserDAO(role);
    }

    public void updateUser(User user, FactoryType factoryType) throws SQLException, NamingException {
        if (user.getRole() == CLIENT) {
            updateClient((Client) user, factoryType);
        } else if (user.getRole() == ADMIN) {
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

    public List<? extends User> getAllClientsPortion(FactoryType factoryType, int portion, int startFrom) throws SQLException, NamingException {
        List<? extends User> clients = null;
        UserDAO userDAO = getUserDAO(CLIENT, factoryType);
        clients = userDAO.getAllUsersPortion(portion, startFrom);
        setFullAccountAndTariff(factoryType, clients);

        return clients;
    }

    public int getTotalClientsAmount(FactoryType mySQL) throws SQLException, NamingException {
        UserDAO userDAO = getUserDAO(CLIENT, mySQL);
        return userDAO.getTotalUsersAmount();
    }

    public void popUpBalance(Client client, BigDecimal amount) throws SQLException, NamingException {
        client.popUpBalance(amount);
        new AccountService().updateAccount(client.getAccount(), FactoryType.MySQL);
    }

    public List<Invoice> getUnpaidInvoices(Client client, FactoryType factoryType) throws SQLException, NamingException {
        Integer accountId = client.getAccount().getId();
        DAOFactory daoFactory = DAOFactory.getDAOFactory(factoryType);
        InvoiceDAO invoiceDAO = daoFactory.getInvoiceDAO();
        return invoiceDAO.getUnpaidInvoices(accountId);
    }
}
