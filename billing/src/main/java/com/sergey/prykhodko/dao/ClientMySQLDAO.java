package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.users.Client;
import com.sergey.prykhodko.users.User;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class ClientMySQLDAO extends UserDAO {
    private String clientQuery = "SELECT  login, password FROM clients WHERE login = '%s'";

    public ClientMySQLDAO() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup("java:comp/env");

        DataSource ds = (DataSource) context.lookup("jdbc/billing");
        connection = ds.getConnection();
        query = clientQuery;
    }
    @Override
    public void storeUser(User user) {
//        final String storeNewClientQuery = "INSERT INTO clients (username, password, email, name, status, id_tariff)" +
//                " VALUES(" + /*user.getLogin() + */")";

    }


}
