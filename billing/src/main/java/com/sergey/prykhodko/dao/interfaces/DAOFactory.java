package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.dao.factories.MySQLDAOFactory;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface DAOFactory {
    UserDAO getUserDAO() throws SQLException, NamingException;

    public static DAOFactory getDAOFactory(FactoryType factoryType){
        switch (factoryType){
            case MySQL:
                return new MySQLDAOFactory();
                default:
                    return null;
        }
    }
}
