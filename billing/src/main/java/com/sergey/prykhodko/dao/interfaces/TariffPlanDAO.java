package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.model.tariffplans.TariffPlan;
import com.sergey.prykhodko.services.TariffPlanBuilder;

import java.sql.SQLException;
import java.util.List;

public interface TariffPlanDAO extends DAO {

    List<TariffPlanBuilder> getAllTariffPlanBuilders() throws SQLException;

    void saveNewTariffPlan(String tariffName) throws SQLException;

    int getIDByName(String tariffName) throws SQLException;

    void addServicesToTariffPlan(int tariffID, String[] servicesIDs) throws SQLException;

    void deleteTariffPlan(String tariffID) throws SQLException;

    TariffPlan getDefaultTariff() throws SQLException;
}
