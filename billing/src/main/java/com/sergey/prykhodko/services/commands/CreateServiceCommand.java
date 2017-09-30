package com.sergey.prykhodko.services.commands;

import com.sergey.prykhodko.services.ServiceManager;
import com.sergey.prykhodko.model.services.Service;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static com.sergey.prykhodko.dao.factories.FactoryType.MySQL;
import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

public class CreateServiceCommand implements Command {
    private static Logger logger = Logger.getLogger(getCurrentClassName());
    private HttpServletRequest request;

    public CreateServiceCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void execute() throws SQLException, NamingException {
        String serviceName = request.getParameter("serviceName");
        request.setAttribute("serviceName", serviceName);
        String charge = request.getParameter("serviceCharge");
        ServiceManager manager = new ServiceManager();
        Service service = manager.createService(serviceName, charge);
        manager.addNewService(MySQL, service);
    }
}
