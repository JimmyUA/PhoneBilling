package com.sergey.prykhodko.managers.commands;

import com.sergey.prykhodko.model.users.User;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class LoginCommand implements Command {
    private User user;
    private HttpServletRequest request;

    public LoginCommand(User user, HttpServletRequest request) {
        this.user = user;
        this.request = request;
    }

    public LoginCommand() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void execute() throws SQLException, NamingException {

    }
}
