package com.sergey.prykhodko.controller.admin.tarifs;

import com.sergey.prykhodko.managers.commands.DeleteTariffCommand;
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

@WebServlet(name = "deleteTariff", urlPatterns = "/deleteTariff")
public class DeleteTariff extends HttpServlet {
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println(request.getParameter("tariffID"));
        try {
            new DeleteTariffCommand(request).execute();
        } catch (SQLException | NamingException e) {
            logger.error(e);
            response.sendRedirect("error.jsp");
            return;
        }
        request.getRequestDispatcher("tariffDeleted.jsp").forward(request, response);
    }
}
