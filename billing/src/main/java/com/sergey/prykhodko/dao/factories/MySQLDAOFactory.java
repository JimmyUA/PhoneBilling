package com.sergey.prykhodko.dao.factories;

import com.sergey.prykhodko.dao.implementations.mySQL.*;
import com.sergey.prykhodko.dao.interfaces.*;
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
    public TariffPlanDAO getTariffPlanDAO() throws SQLException, NamingException {
        return new TariffPlanMySqlDAO();
    }

    @Override
    public ServiceDAO getServiceDAO() throws SQLException, NamingException {
        return new ServiceMySqlDAO();
    }

    @Override
    public AccountDAO getAccountDAO() throws SQLException, NamingException {
        return new AccountMySqlDAO();
    }

    @Override
    public InvoiceDAO getInvoiceDAO() throws SQLException, NamingException {
        return new InvoiceMySqlDAO();
    }
}
