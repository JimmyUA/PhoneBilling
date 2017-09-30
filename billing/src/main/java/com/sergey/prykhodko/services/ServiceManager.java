package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.ServiceDAO;
import com.sergey.prykhodko.model.services.Service;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

public class ServiceManager {
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    public List<Service> getServiceByID(int tariffPlanID, FactoryType factoryType) throws SQLException, NamingException {
        ServiceDAO serviceDAO = getServiceDAO(factoryType);
        List<Service> services = serviceDAO.getServicesByTariffPlanID(tariffPlanID);
        return services;
    }

    private ServiceDAO getServiceDAO(FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory factory = DAOFactory.getDAOFactory(factoryType);
        return factory.getServiceDAO();
    }

    public List<Service> getAllServices(FactoryType factoryType) throws SQLException, NamingException {
        ServiceDAO serviceDAO = getServiceDAO(factoryType);
        List<Service> allServices = serviceDAO.getAllServices();
        return allServices;
    }

    public void addNewService(FactoryType factoryType, Service service) throws SQLException, NamingException {
        ServiceDAO serviceDAO = getServiceDAO(factoryType);
        serviceDAO.addNewService(service);
    }

    public Service createService(String serviceName, String charge) {
        Service service = new Service(serviceName, new BigDecimal(charge));
        return service;
    }
}
