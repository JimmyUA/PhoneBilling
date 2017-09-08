package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.users.Client;
import com.sergey.prykhodko.users.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminMySqlDAO extends UserDAO {
    private String adminQuery = "SELECT  login, password FROM admins WHERE login = '%s'";


    public AdminMySqlDAO() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup("java:comp/env");

        DataSource ds = (DataSource) context.lookup("jdbc/billing");
        connection = ds.getConnection();
        query = adminQuery;
    }



}
