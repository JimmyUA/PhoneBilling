package com.sergey.prykhodko.controller.admin;

import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.managers.UsersManager;
import com.sergey.prykhodko.model.users.Client;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;

@WebServlet("/clientsList")
public class ClientsList extends HttpServlet{

    private static Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = Collections.EMPTY_LIST;
        try {
           clients = new UsersManager().getAllClients(FactoryType.MySQL);
        } catch (SQLException | NamingException e) {
            logger.error(e);
            response.sendRedirect("error.jsp");
        }
        request.getSession().setAttribute("clients", clients);
        request.getRequestDispatcher("clientsList.jsp").forward(request, response);
    }
}
