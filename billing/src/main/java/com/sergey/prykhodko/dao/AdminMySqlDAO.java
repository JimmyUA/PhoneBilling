package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.users.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

public class AdminMySqlDAO extends UserDAO {
    private String addAdminQuery = "SELECT  login, password FROM admins WHERE login = '%s'";


    public AdminMySqlDAO() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup("java:comp/env");

        DataSource ds = (DataSource) context.lookup("jdbc/billing");
        connection = ds.getConnection();
        addQuery = addAdminQuery;
    }


    @Override
    public void addUser(User user) throws SQLException {

    }
}
