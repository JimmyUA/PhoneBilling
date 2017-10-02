package com.sergey.prykhodko.controller;


import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.model.account.Account;
import com.sergey.prykhodko.services.AccountService;
import com.sergey.prykhodko.services.ClientValidator;
import com.sergey.prykhodko.services.TariffPlanService;
import com.sergey.prykhodko.util.PasswordEncoder;
import com.sergey.prykhodko.services.UserService;
import com.sergey.prykhodko.services.commands.RegisterClient;
import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.model.users.ClientBuilder;
import com.sergey.prykhodko.util.Accounts;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;


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
        String confirmPassword = request.getParameter("confirmPassword");

        System.out.println(fullName);

        Client client;
        if (password.equals(confirmPassword)) {
            password = PasswordEncoder.encodePassword(password);
            try {
                client = new ClientBuilder()
                        .setLogin(login)
                        .setPassword(password)
                        .setEmail(email)
                        .setActive(false)
                        .setFullName(fullName)
                        .setTariffPlan(new TariffPlanService().getDefaultTariff(FactoryType.MySQL))
                        .setAccount(new AccountService().getNewAccount())
                        .build();
            } catch (SQLException | NamingException e) {
                logger.error(e);
                response.sendRedirect("error.jsp");
                return;
            }
        } else {
            logger.error("Client was not registered, reason \"passwords is not equals\" " +
                    "entered password: " + "\"" + password + "\"" +
                    ", entered confirmation: " + "\"" + confirmPassword + "\"");
            response.sendRedirect("register.jsp");
            return;
        }
        if (new ClientValidator().validate(client)) {
            try {
                UserService.executeCommand(new RegisterClient(client));
                request.getSession().setAttribute("user", client);
                request.getRequestDispatcher("welcomeNewClient.jsp").forward(request, response);
            } catch (SQLException | NamingException e) {
                logger.error(e);
            }
        } else {
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
