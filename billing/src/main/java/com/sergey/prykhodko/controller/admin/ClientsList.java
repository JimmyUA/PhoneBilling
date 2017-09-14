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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;

@WebServlet("/clientsList")
public class ClientsList extends HttpServlet {

    private static Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    public void init() throws ServletException {
        int totalClientsAmount = 0;
        try {
            totalClientsAmount = new UsersManager().getTotalClientsAmount(FactoryType.MySQL);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        getServletContext().setAttribute("lastPage", "" + (totalClientsAmount/2 + 1));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("lastPage", Integer.parseInt((String) getServletContext().getAttribute("lastPage")));
        int startFrom = 0;
        //TODO get portion from context
        int portion = 2;
        startFrom = changeStartPosition(request, startFrom);

        List<Client> clients = new ArrayList<>();
        try {
            clients = new UsersManager().getAllClientsPortion(FactoryType.MySQL, portion, startFrom);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        request.getSession().setAttribute("clients", clients);
        request.getSession().setAttribute("pageNumber", startFrom + 1);
        request.getRequestDispatcher("clientsList.jsp").forward(request, response);
    }

    private int changeStartPosition(HttpServletRequest request, int startFrom) {
        try {
            startFrom = (Integer) request.getSession().getAttribute("pageNumber") - 1;
            if (request.getParameter("previous") != null) {
                startFrom--;
            } else if (request.getParameter("next") != null) {
                startFrom++;
            }
        } catch (NullPointerException e) {
        }
        return startFrom;
    }

}
