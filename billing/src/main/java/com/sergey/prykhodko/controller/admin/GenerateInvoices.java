package com.sergey.prykhodko.controller.admin;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.services.InvoiceService;
import com.sergey.prykhodko.services.commands.GenerateInvoicesCommand;
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

@WebServlet(name = "generateInvoices", urlPatterns = "/generateInvoices")
public class GenerateInvoices extends HttpServlet{

    private static Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        try {
            new GenerateInvoicesCommand().execute();
        } catch (SQLException | NamingException e) {
            logger.error(e);
            StackTraceElement[] trace = e.getStackTrace();
            for (StackTraceElement line:trace
                 ) {
                logger.info(line.toString());
            }
            response.sendRedirect("error.jsp");
            return;
        }
        request.getRequestDispatcher("clientsList.jsp").forward(request, response);
    }
}
