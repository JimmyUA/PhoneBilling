package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.dao.factories.MySQLDAOFactory;
import com.sergey.prykhodko.users.UserRole;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface DAOFactory {
    UserDAO getUserDAO(UserRole role) throws SQLException, NamingException;

    public static DAOFactory getDAOFactory(FactoryType factoryType){
        switch (factoryType){
            case MySQL:
                return new MySQLDAOFactory();
                default:
                    return new MySQLDAOFactory();
        }
    }
}
