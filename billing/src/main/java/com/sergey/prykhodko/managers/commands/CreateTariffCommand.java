package com.sergey.prykhodko.managers.commands;

import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.managers.TariffManager;
import com.sergey.prykhodko.managers.TariffPlanBuilder;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static com.sergey.prykhodko.dao.FactoryType.*;

public class CreateTariffCommand implements Command {
    private HttpServletRequest request;

    public CreateTariffCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void execute() throws SQLException, NamingException {
        String tariffName = request.getParameter("tariffName");
        String[] servicesIDs = request.getParameterValues("services");
        TariffManager tariffManager = new TariffManager();
        tariffManager.addNewTariffPlan(MySQL, tariffName);
        int newTariffID = tariffManager.getIDByName(MySQL, tariffName);
        tariffManager.addServicesToTariff(newTariffID, servicesIDs, MySQL);
    }
}
