package com.sergey.prykhodko.dao.implementations.mySQL;

import com.sergey.prykhodko.dao.interfaces.TariffPlanDAO;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;
import com.sergey.prykhodko.services.TariffPlanBuilder;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.sergey.prykhodko.connection.pool.ConnectionPool.getConnection;
import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

public class TariffPlanMySqlDAO implements TariffPlanDAO {
    private static final String GET_ALL = "SELECT * FROM tariffs";
    private static final String GET_DEFAULT_TARIFF = "SELECT * FROM tariffs WHERE id_tariff=1";
    private static final String ADD_TARIFF = "INSERT INTO tariffs (name) VALUES (?)";
    private static final String GET_ID_BY_NAME = "SELECT id_tariff FROM tariffs WHERE name=?";
    private static final String ADD_CONNECT_SERVICES = "INSERT INTO tariff_service VALUES (?, ?)";
    private static final String DELETE_TAFIFF = "DELETE FROM tariffs WHERE id_tariff=?";
    private static final String DELETE_CONNECTED_SERVICES = "DELETE FROM tariffs WHERE id_tariff=?";

    private static Logger logger = Logger.getLogger(getCurrentClassName());

    private Connection connection = getConnection();

    public TariffPlanMySqlDAO() throws NamingException, SQLException {

    }

    @Override
    public List<TariffPlanBuilder> getAllTariffPlanBuilders() throws SQLException {
        List<TariffPlanBuilder> tariffPlanBuilders = new ArrayList<>();

        TariffPlanBuilder tariffPlanBuilder;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                tariffPlanBuilder = buildTariffPlanBuilder(resultSet);
                tariffPlanBuilders.add(tariffPlanBuilder);
            }
            return tariffPlanBuilders;
        } finally {
            closeConnection();
        }
    }

    @Override
    public void saveNewTariffPlan(String tariffName) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_TARIFF)) {
            statement.setString(1, tariffName);
            statement.execute();
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    private TariffPlanBuilder buildTariffPlanBuilder(ResultSet resultSet) throws SQLException {
        return new TariffPlanBuilder()
                .setId(resultSet.getInt(1))
                .setName(resultSet.getString(2));
    }

    @Override
    public int getIDByName(String tariffName) throws SQLException {
        int id = -1;
        try (PreparedStatement statement = connection.prepareStatement(GET_ID_BY_NAME)) {
            statement.setString(1, tariffName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return id;
    }

    @Override
    public void addServicesToTariffPlan(int tariffID, String[] servicesIDs) throws SQLException {
        String serviceID;

        try (PreparedStatement statement = connection.prepareStatement(ADD_CONNECT_SERVICES)) {
            statement.setInt(1, tariffID);
            for (String servicesID : servicesIDs) {
                serviceID = servicesID;
                statement.setString(2, serviceID);
                statement.execute();
            }
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deleteTariffPlan(String tariffID) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_TAFIFF)) {
            statement.setString(1, tariffID);
            statement.execute();
        }
        try (PreparedStatement statement = connection.prepareStatement(DELETE_CONNECTED_SERVICES)) {
            statement.setString(1, tariffID);
            statement.execute();
        } finally {
            closeConnection();
        }
    }

    @Override
    public TariffPlan getDefaultTariff() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_DEFAULT_TARIFF);
            TariffPlan defaultTariff = new TariffPlan();
            if (resultSet.next()){
                defaultTariff.setId(resultSet.getInt(1));
                defaultTariff.setName(resultSet.getString(2));
            }
            return defaultTariff;
        } finally {
            closeConnection();
        }
    }
}
