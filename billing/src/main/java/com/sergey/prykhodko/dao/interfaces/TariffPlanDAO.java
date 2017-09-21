package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.managers.TariffPlanBuilder;

import java.sql.SQLException;
import java.util.List;

public interface TariffPlanDAO extends DAO{
    void closeConnection() throws SQLException;

    List<TariffPlanBuilder> getAllTariffPlanBuilders() throws SQLException;

    void saveNewTariffPlan(String tariffName) throws SQLException;

    int getIDByName(String tariffName) throws SQLException;

    void addServicesToTariffPlan(int tariffID, String[] servicesIDs) throws SQLException;
}
