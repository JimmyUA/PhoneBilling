package com.sergey.prykhodko.controller;


import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.managers.ClientValidator;
import com.sergey.prykhodko.managers.PasswordEncoder;
import com.sergey.prykhodko.managers.UsersManager;
import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.model.users.ClientBuilder;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;


@WebServlet("/registration")
public class Registration extends HttpServlet {

    private static Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("full-name");
        String login = request.getParameter("login");
        String email = request.getParameter("e-mail");
        String password = request.getParameter("password");
        password = PasswordEncoder.encodePassword(password);

        Client client = new ClientBuilder()
                .setLogin(login)
                .setPassword(password)
                .setEmail(email)
                .setActive(false)
                .setFullName(fullName)
                .build();

        if (new ClientValidator().validate(client)) {
            try {
                new UsersManager().addUserToDB(client, FactoryType.MySQL);
                logger.info(client.getLogin() + " is registered as a client");
                request.setAttribute("user", client);
                request.getRequestDispatcher("welcomeNewClient.jsp").forward(request, response);
            } catch (SQLException | NamingException e) {
                logger.error(e);
            }
        }
        else {
            logger.error("Client with e-mail: " + client.getEmail() + " is not registered");
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
    }
}