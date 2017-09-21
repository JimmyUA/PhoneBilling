package com.sergey.prykhodko.managers.commands;

import com.sergey.prykhodko.managers.TariffManager;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static com.sergey.prykhodko.dao.factories.FactoryType.*;
import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;

public class CreateTariffCommand implements Command {
    private static Logger logger = Logger.getLogger(getCurrentClassName());
    private HttpServletRequest request;

    public CreateTariffCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void execute() throws SQLException, NamingException {
        String tariffName = request.getParameter("tariffName");
        request.setAttribute("tariffName", tariffName);
        String[] servicesIDs = request.getParameterValues("services");
        TariffManager tariffManager = new TariffManager();
        tariffManager.addNewTariffPlan(MySQL, tariffName);
        if(servicesIDs != null) {
            int newTariffID = tariffManager.getIDByName(MySQL, tariffName);
            tariffManager.addServicesToTariff(newTariffID, servicesIDs, MySQL);
        }
    }
}
