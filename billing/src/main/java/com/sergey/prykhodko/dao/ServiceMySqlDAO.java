package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.dao.interfaces.ServiceDAO;
import com.sergey.prykhodko.managers.ServiceBuilder;
import com.sergey.prykhodko.model.services.Service;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;

public class ServiceMySqlDAO implements ServiceDAO {
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    private Connection connection;
    private Statement statement;

    public ServiceMySqlDAO() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup("java:comp/env");


        DataSource ds = (DataSource) context.lookup("jdbc/billing");
        connection = ds.getConnection();
        statement = connection.createStatement();
    }

    @Override
    public List<Service> getServicesByTariffPlanID(int tariffID) throws SQLException {
        final String getIDsByTariffIDQuery = "SELECT * FROM tariff_service JOIN services " +
                "ON tariff_service.id_service = services.id_service WHERE id_tariff=" + tariffID;

        List<Service> services = new ArrayList<>(0);
        Service service;
        try(ResultSet resultSet = statement.executeQuery(getIDsByTariffIDQuery)){
            while (resultSet.next()){
                service = buildServiceFromJoinTables(resultSet);
                services.add(service);
            }
        }

        return services;
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
        final String getAllServices = "SELECT * FROM services";

        List<Service> services = new ArrayList<>(0);
        Service service;
        try(ResultSet resultSet = statement.executeQuery(getAllServices)){
            while (resultSet.next()){
                service = buildService(resultSet);
                services.add(service);
            }
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
    public void closeConnection() throws SQLException {
        statement.close();
        connection.close();

    }
}
