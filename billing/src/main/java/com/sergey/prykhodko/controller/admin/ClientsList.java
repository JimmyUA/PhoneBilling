package com.sergey.prykhodko.controller.admin;

import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.managers.UsersManager;
import com.sergey.prykhodko.model.users.Client;
import org.apache.log4j.Logger;

import javax.jws.WebParam;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
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

@WebServlet(urlPatterns = "/clientsList", name = "clientList",
            initParams = {
                @WebInitParam(name = "portion", value = "3")
            })
public class ClientsList extends HttpServlet {

    private int portion;
    private int pageNumber;
    private static Logger logger = Logger.getLogger(getCurrentClassName());


    @Override
    public void init() throws ServletException {
        portion = Integer.parseInt(getServletConfig().getInitParameter("portion"));
        pageNumber = 1;
        int totalClientsAmount = 0;
        try {
            totalClientsAmount = new UsersManager().getTotalClientsAmount(FactoryType.MySQL);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        int lastPage = totalClientsAmount / portion + (totalClientsAmount % 2 == 0 ? 0 : 1);

        getServletContext().setAttribute("lastPage", "" + lastPage);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int startFrom = 0;
        int portion = Integer.parseInt(getServletConfig().getInitParameter("portion"));
        startFrom = changeStartPosition(request, startFrom);

        List<Client> clients = new ArrayList<>();
        try {
            clients = new UsersManager().getAllClientsPortion(FactoryType.MySQL, portion, startFrom);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        request.getSession().setAttribute("clients", clients);
        request.getSession().setAttribute("pageNumber", pageNumber);
        request.getSession().setAttribute("startFrom", startFrom);
        request.getRequestDispatcher("clientsList.jsp").forward(request, response);
    }

    private int changeStartPosition(HttpServletRequest request, int startFrom) {
        try {
            startFrom = (Integer) request.getSession().getAttribute("startFrom");
            if (request.getParameter("previous") != null) {
                pageNumber--;
                startFrom -= portion;
            } else if (request.getParameter("next") != null) {
                pageNumber++;
                startFrom += portion;
            }
        } catch (NullPointerException e) {
            return 1;
        }
        return startFrom;
    }

}