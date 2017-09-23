package com.sergey.prykhodko.dao.implementations.mySQL;

import com.sergey.prykhodko.dao.interfaces.ServiceDAO;
import com.sergey.prykhodko.managers.ServiceBuilder;
import com.sergey.prykhodko.model.services.Service;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.sergey.prykhodko.connection.pool.ConnectionPool.getConnection;
import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;

public class ServiceMySqlDAO implements ServiceDAO {
    private static final String GET_IDs_BY_TARIFF_ID = "SELECT * FROM tariff_service JOIN services " +
            "ON tariff_service.id_service = services.id_service WHERE id_tariff=?";
    private static final String GET_ALL = "SELECT * FROM services";
    private static final String ADD_SERVICE = "INSERT INTO services (name, charge_for_month) VALUES (?, ?)";
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    private Connection connection = getConnection();

    public ServiceMySqlDAO() throws SQLException, NamingException {
    }


    @Override
    public List<Service> getServicesByTariffPlanID(int tariffID) throws SQLException {

        List<Service> services = new ArrayList<>(0);
        Service service;
        try(PreparedStatement statement = connection.prepareStatement(GET_IDs_BY_TARIFF_ID)){
            statement.setInt(1, tariffID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                service = buildServiceFromJoinTables(resultSet);
                services.add(service);
            }
        } finally {
            closeConnection();
        }

        return services;
    }

    private void closeConnection() throws SQLException {
        if (connection != null){
            connection.close();
        }
    }

    private Service buildServiceFromJoinTables(ResultSet resultSet) throws SQLException {
        Service service = new ServiceBuilder()
                .setID(resultSet.getInt(3))
                .setName(resultSet.getString(4))
                .setCharge(new BigDecimal(resultSet.getString(5)))
                .build();
        return service;
    }

    @Override
    public List<Service> getAllServices() throws SQLException {

        List<Service> services = new ArrayList<>(0);
        Service service;
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()){
                service = buildService(resultSet);
                services.add(service);
            }
        } finally {
            closeConnection();
        }
        return services;
    }

    private Service buildService(ResultSet resultSet) throws SQLException {
        Service service = new ServiceBuilder()
                .setID(resultSet.getInt(1))
                .setName(resultSet.getString(2))
                .setCharge(new BigDecimal(resultSet.getString(3)))
                .build();
        return service;
    }

    @Override
    public void addNewService(Service service) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(ADD_SERVICE)) {
            statement.setString(1, service.getName());
            statement.setString(2, service.getChargePerMonth().toString());
            statement.execute();
        } finally {
            closeConnection();
        }
    }
}
