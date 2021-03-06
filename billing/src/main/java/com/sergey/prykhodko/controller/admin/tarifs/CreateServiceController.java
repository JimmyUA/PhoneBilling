package com.sergey.prykhodko.controller.admin.tarifs;

import com.sergey.prykhodko.services.commands.CreateServiceCommand;
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

@WebServlet(name = "createService", urlPatterns = "/createService")
public class CreateServiceController extends HttpServlet {
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("newServiceCreation.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            new CreateServiceCommand(request).execute();
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        request.getRequestDispatcher("newServiceCreated.jsp").forward(request, response);
    }
}
