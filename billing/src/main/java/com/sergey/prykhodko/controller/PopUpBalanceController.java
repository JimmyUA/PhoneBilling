package com.sergey.prykhodko.controller;

import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.services.commands.PopUpBalanceCommand;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

@WebServlet(name = "popUpBalance", urlPatterns = "/popUpBalance")
public class PopUpBalanceController extends HttpServlet {
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("popUpBalance.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BigDecimal popUpAmount = new BigDecimal(request.getParameter("amount"));
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("user");
        try {
            new PopUpBalanceCommand(client, popUpAmount).execute();
        } catch (SQLException | NamingException e) {
            logger.error(e);
            response.sendRedirect("error.jsp");
        }
        session.setAttribute("user", client);
        response.sendRedirect("clientCabinet.jsp");
    }
}
