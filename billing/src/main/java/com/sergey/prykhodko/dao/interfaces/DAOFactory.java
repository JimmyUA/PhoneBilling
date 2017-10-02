package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.dao.factories.MySQLDAOFactory;
import com.sergey.prykhodko.model.users.UserRole;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface DAOFactory {
    static DAOFactory getDAOFactory(FactoryType factoryType){
        switch (factoryType){
            case MySQL:
                return new MySQLDAOFactory();
                default:
                    return new MySQLDAOFactory();
        }
    }
    UserDAO getUserDAO(UserRole role) throws SQLException, NamingException;

    TariffPlanDAO getTariffPlanDAO() throws SQLException, NamingException;

    ServiceDAO getServiceDAO() throws SQLException, NamingException;

    AccountDAO getAccountDAO() throws SQLException, NamingException;

    InvoiceDAO getInvoiceDAO() throws SQLException, NamingException;

    PaymentDAO getPaymentDAO() throws SQLException, NamingException;
}
