package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.TariffPlanDAO;
import com.sergey.prykhodko.model.services.Service;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

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
        return tariffPlanDAO.getAllTariffPlanBuilders();
    }

    private TariffPlanDAO getTariffPlanDAO(FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory factory = DAOFactory.getDAOFactory(factoryType);
        return factory.getTariffPlanDAO();
    }

    public void addNewTariffPlan(FactoryType factoryType, String tariffName) throws SQLException, NamingException {
        TariffPlanDAO tariffPlanDAO = getTariffPlanDAO(factoryType);
        tariffPlanDAO.saveNewTariffPlan(tariffName);
    }

    public int getIDByName(FactoryType factoryType, String tariffName) throws SQLException, NamingException {
        TariffPlanDAO tariffPlanDAO = getTariffPlanDAO(factoryType);
        return tariffPlanDAO.getIDByName(tariffName);
    }

    public void addServicesToTariff(int tariffID, String[] servicesIDs, FactoryType factoryType) throws SQLException, NamingException {
        TariffPlanDAO tariffPlanDAO = getTariffPlanDAO(factoryType);
        tariffPlanDAO.addServicesToTariffPlan(tariffID, servicesIDs);
    }

    public void deleteTariff(FactoryType factoryType, String tariffID) throws SQLException, NamingException {
        TariffPlanDAO tariffPlanDAO = getTariffPlanDAO(factoryType);
        tariffPlanDAO.deleteTariffPlan(tariffID);
    }
}
