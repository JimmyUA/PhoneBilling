package com.sergey.prykhodko.controller;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.services.UsersService;
import com.sergey.prykhodko.util.PasswordEncoder;
import com.sergey.prykhodko.model.users.User;
import com.sergey.prykhodko.model.users.UserRole;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("loginForm:login");
        String password = request.getParameter("loginForm:password");

        User user = null;
        try {
            user = new UsersService().getUserByLogin(login, FactoryType.MySQL);
        } catch (SQLException | NamingException e) {
            logger.error(e);
            response.sendRedirect("/login");
            return;
        }
        if (user == null) {
            logger.info("User with login: \"" + login + "\" was not found");
            request.getSession().setAttribute("errorMessage", "Login is not registered");
            response.sendRedirect("/login");
        } else if (passwordIsWrong(password, user)) {
            logger.info("User with login: \"" + login + "\" have entered wrong password");
            request.getSession().setAttribute("errorMessage", "Password is wrong");
            response.sendRedirect("login.jsp");
        } else {
            if (user.getRole() == UserRole.CLIENT) {
                logger.info(user.getLogin() + " logged in");
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("clientCabinet.jsp").forward(request, response);
            } else if (user.getRole() == UserRole.ADMIN) {
                logger.info(user.getLogin() + " logged in ");
                request.setAttribute("user", user);
                request.getRequestDispatcher("adminCabinet.jsp").forward(request, response);
            } else {
                response.sendRedirect("register.jsp");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);

    }

    private boolean passwordIsWrong(String password, User user) {
        password = PasswordEncoder.encodePassword(password);
        return !password.equals(user.getPassword());
    }
}
