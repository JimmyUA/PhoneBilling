package com.sergey.prykhodko.services.commands;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.services.TariffManager;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

public class DeleteTariffCommand implements Command{
    private static Logger logger = Logger.getLogger(getCurrentClassName());
    private HttpServletRequest request;

    public DeleteTariffCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void execute() throws SQLException, NamingException {
        String tariffID = request.getParameter("tariffID");
        request.setAttribute("tariffID", tariffID);
        TariffManager tariffManager = new TariffManager();
        tariffManager.deleteTariff(FactoryType.MySQL, tariffID);
    }
}
