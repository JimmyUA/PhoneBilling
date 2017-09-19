package com.sergey.prykhodko.dao.factories;

import com.sergey.prykhodko.dao.AdminMySqlDAO;
import com.sergey.prykhodko.dao.ClientMySqlDAO;
import com.sergey.prykhodko.dao.TariffPlanDAO;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.model.users.UserRole;

import javax.naming.NamingException;
import java.sql.SQLException;

public class MySQLDAOFactory implements DAOFactory {

    @Override
    public UserDAO getUserDAO(UserRole role) throws SQLException, NamingException {
        switch (role) {
            case CLIENT:
                return new ClientMySqlDAO();
            case ADMIN:
                return new AdminMySqlDAO();
            case GUEST:
                return null;
                default:
                    return null;
        }
    }

    @Override
    public TariffPlanDAO getTariffDAO() throws SQLException, NamingException {
        return new TariffPlanMySqlDAO();
    }
}
