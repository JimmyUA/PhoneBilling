package com.sergey.prykhodko.managers;

import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.TariffPlanDAO;
import com.sergey.prykhodko.model.services.Service;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;

public class TariffManager {

    private static Logger logger = Logger.getLogger(getCurrentClassName());

    public List<TariffPlan> getAllTariffPlans(FactoryType factoryType) throws SQLException, NamingException {
        List<TariffPlan> tariffPlans;

        List<TariffPlanBuilder> builders = getTariffPlanBuilders(factoryType);
        tariffPlans = buildTariffPlans(builders, factoryType);

        return tariffPlans;
    }

    private List<TariffPlan> buildTariffPlans(List<TariffPlanBuilder> builders, FactoryType factoryType) throws SQLException, NamingException {
        List<TariffPlan> tariffPlans = new ArrayList<>();
        List<Service> services;

        for (TariffPlanBuilder builder : builders
             ) {
            services = getServicesForTariffPlan(builder, factoryType);
            builder.setServises(services);
            tariffPlans.add(builder.build());
        }
        return tariffPlans;
    }

    private List<Service> getServicesForTariffPlan(TariffPlanBuilder builder, FactoryType factoryType) throws SQLException, NamingException {
        return new ServiceManager().getServiceByID(builder.getID(), factoryType);
    }

    private List<TariffPlanBuilder> getTariffPlanBuilders(FactoryType factoryType) throws SQLException, NamingException {
        TariffPlanDAO tariffPlanDAO = getTariffPlanDAO(factoryType);
        List<TariffPlanBuilder> builders = tariffPlanDAO.getAllTariffPlanBuilders();
        tariffPlanDAO.closeConnection();
        return builders;
    }

    private TariffPlanDAO getTariffPlanDAO(FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory factory = DAOFactory.getDAOFactory(factoryType);
        return factory.getTariffPlanDAO();
    }
}
