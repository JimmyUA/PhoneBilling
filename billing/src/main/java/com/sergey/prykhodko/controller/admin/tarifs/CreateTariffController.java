package com.sergey.prykhodko.controller.admin.tarifs;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.managers.ServiceManager;
import com.sergey.prykhodko.managers.commands.CreateTariffCommand;
import com.sergey.prykhodko.model.services.Service;
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

import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;

@WebServlet(name = "createTariff", urlPatterns = "/createTariff")
public class CreateTariff extends HttpServlet {
        private static Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            new CreateTariffCommand(request).execute();
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        request.getRequestDispatcher("newTariffCreated.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Service> services;
        try {
            services = new ServiceManager().getAllServices(FactoryType.MySQL);
        } catch (SQLException | NamingException e) {
            logger.error(e);
            response.sendRedirect("error.jsp");
            return;
        }
        request.setAttribute("services", services);
        request.getRequestDispatcher("createTariff.jsp").forward(request, response);
    }
}
