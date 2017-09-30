package com.sergey.prykhodko.controller.admin.tarifs;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.services.TariffManager;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;
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

import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

@WebServlet(name = "tariffs", urlPatterns = "/tariffs")
public class TariffsController extends HttpServlet{
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TariffPlan> tariffPlans = Collections.EMPTY_LIST;
        try {
            tariffPlans = new TariffManager().getAllTariffPlans(FactoryType.MySQL);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }

        request.getSession().setAttribute("tariffs", tariffPlans);
        request.getRequestDispatcher("tariffPlans.jsp").forward(request, response);
    }
}
