package com.sergey.prykhodko.controller.admin;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.services.UsersService;
import com.sergey.prykhodko.model.users.User;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

@WebServlet(urlPatterns = "/clientsList", name = "clientList",
            initParams = {
                @WebInitParam(name = "portion", value = "5")
            })
public class ClientsList extends HttpServlet {

    private int portion;
    private int pageNumber;
    private int lastPage;
    private static Logger logger = Logger.getLogger(getCurrentClassName());


    @Override
    public void init() throws ServletException {
        portion = Integer.parseInt(getServletConfig().getInitParameter("portion"));
        pageNumber = 1;
        int totalClientsAmount = 0;
        try {
            totalClientsAmount = new UsersService().getTotalClientsAmount(FactoryType.MySQL);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        lastPage = (totalClientsAmount / portion) + (totalClientsAmount % portion == 0 ? 0 : 1);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int startFrom = 0;
        int portion = Integer.parseInt(getServletConfig().getInitParameter("portion"));
        changeStartPosition(request);

        List<? extends User> clients = new ArrayList<>();
        try {
            clients = new UsersService().getAllClientsPortion(FactoryType.MySQL, portion, pageNumber);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        request.getSession().setAttribute("clients", clients);
        request.getSession().setAttribute("lastPage", lastPage);
        request.getSession().setAttribute("pageNumber", pageNumber);
//        request.getSession().setAttribute("startFrom", startFrom);
        request.getRequestDispatcher("clientsList.jsp").forward(request, response);
    }

    private void changeStartPosition(HttpServletRequest request) {
            if (request.getParameter("previous") != null) {
                pageNumber--;
            } else if (request.getParameter("next") != null) {
                pageNumber++;
            }
    }

}
