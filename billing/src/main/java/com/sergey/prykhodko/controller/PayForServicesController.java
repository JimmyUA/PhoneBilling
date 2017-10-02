package com.sergey.prykhodko.controller;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.exceptions.NotEnoughMoneyException;
import com.sergey.prykhodko.model.account.Invoice;
import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.services.UsersService;
import com.sergey.prykhodko.services.commands.PayForAllCommand;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

@WebServlet(name = "payForServices", urlPatterns = "/payForServices")
public class PayForServicesController extends HttpServlet{

    private static Logger logger = Logger.getLogger(getCurrentClassName());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = (Client) request.getSession().getAttribute("user");
        List<Invoice> unpaidInvoices;
        try {
            unpaidInvoices = new UsersService().getUnpaidInvoices(client, FactoryType.MySQL); // TODO get invoices from client
        } catch (SQLException | NamingException e) {
            logger.error(e);
            response.sendRedirect("error.jsp");
            return;
        }
        request.getSession().setAttribute("unpaidInvoices", unpaidInvoices);
        request.getRequestDispatcher("payForServices.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = (Client) request.getSession().getAttribute("user");

        try {
            new PayForAllCommand(request).execute();
        } catch (NotEnoughMoneyException n){
            logger.info(n);
            request.setAttribute("errorMessage", n.getMessage());
            request.getRequestDispatcher("notEnoughMoney.jsp").forward(request, response);
        }
        catch (SQLException | NamingException e) {
            logger.error(e);
            response.sendRedirect("error.jsp");
            return;
        }
        request.getRequestDispatcher("payForServices.jsp").forward(request, response);

    }
}
