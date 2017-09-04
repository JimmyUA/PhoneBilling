package com.sergey.prykhodko.dao.factories;

import com.sergey.prykhodko.dao.ClientMySQLDAO;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.UserDAO;

import javax.naming.NamingException;
import java.sql.SQLException;

public class MySQLDAOFactory implements DAOFactory {

    @Override
    public UserDAO getUserDAO() throws SQLException, NamingException {
        return new ClientMySQLDAO();
    }
}
