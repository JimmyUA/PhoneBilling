package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.model.services.Service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDAO extends DAO {

    List<Service> getServicesByTariffPlanID(int TariffID) throws SQLException;

    List<Service> getAllServices() throws SQLException;

    void addNewService(Service service) throws SQLException;
}
