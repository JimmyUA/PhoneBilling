package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.dao.TariffPlanDAO;
import com.sergey.prykhodko.dao.factories.MySQLDAOFactory;
import com.sergey.prykhodko.model.users.UserRole;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface DAOFactory {
    UserDAO getUserDAO(UserRole role) throws SQLException, NamingException;

    TariffPlanDAO getTariffDAO() throws SQLException, NamingException;

    static DAOFactory getDAOFactory(FactoryType factoryType){
        switch (factoryType){
            case MySQL:
                return new MySQLDAOFactory();
                default:
                    return new MySQLDAOFactory();
        }
    }

}
