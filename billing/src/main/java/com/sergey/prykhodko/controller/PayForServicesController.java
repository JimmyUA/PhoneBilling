package com.sergey.prykhodko.controller;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.model.account.Invoice;
import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.services.UsersService;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "payForServices", urlPatterns = "/payForServices")
public class PayForServicesController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = (Client) request.getSession().getAttribute("user");
        List<Invoice> unpaidInvoices;
        try {
            unpaidInvoices = new UsersService().getUnpaidInvoices(client, FactoryType.MySQL);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
            return;
        }
        request.getSession().setAttribute("unpaidInvoices", unpaidInvoices);
        request.getRequestDispatcher("payForServices.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
