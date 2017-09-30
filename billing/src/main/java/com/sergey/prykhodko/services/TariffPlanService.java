package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.TariffPlanDAO;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;

import javax.naming.NamingException;
import java.sql.SQLException;

public class TariffPlanService implements Service{


    public TariffPlan getDefaultTariff(FactoryType factoryType) throws SQLException, NamingException {
        TariffPlanDAO tariffPlanDAO = getTariffPlanDAO(factoryType);
        return tariffPlanDAO.getDefaultTariff();
    }

    public TariffPlanDAO getTariffPlanDAO(FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(factoryType);
        return daoFactory.getTariffPlanDAO();
    }
}
