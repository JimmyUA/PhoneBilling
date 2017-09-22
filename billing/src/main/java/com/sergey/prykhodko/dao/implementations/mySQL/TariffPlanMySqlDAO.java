package com.sergey.prykhodko.dao.implementations.mySQL;

import com.sergey.prykhodko.dao.interfaces.TariffPlanDAO;
import com.sergey.prykhodko.managers.TariffPlanBuilder;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;

public class TariffPlanMySqlDAO implements TariffPlanDAO {
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    private Connection connection;
    private Statement statement;

    public TariffPlanMySqlDAO() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup("java:comp/env");


        DataSource ds = (DataSource) context.lookup("jdbc/billing");
        connection = ds.getConnection();
        statement = connection.createStatement();
    }

    @Override
    public List<TariffPlanBuilder> getAllTariffPlanBuilders() throws SQLException {
        List<TariffPlanBuilder> tariffPlanBuilders = new ArrayList<>();
        
        final String getAllQuery = "Select * FROM tariffs";
        
        TariffPlanBuilder tariffPlanBuilder;
        try(ResultSet resultSet = statement.executeQuery(getAllQuery)){
            while (resultSet.next()) {
                tariffPlanBuilder = buildTariffPlanBuilder(resultSet);
                tariffPlanBuilders.add(tariffPlanBuilder);
            }
            return tariffPlanBuilders;
        }
    }

    @Override
    public void saveNewTariffPlan(String tariffName) throws SQLException {
        String addNewTariffQuery = "INSERT INTO tariffs (name) VALUES ('" + tariffName + "')";
        boolean isAdded = !(statement.execute(addNewTariffQuery));
        if (isAdded){
            logger.info("Created new tariff plan \"" + tariffName + "\"");
        }
    }

    private TariffPlanBuilder buildTariffPlanBuilder(ResultSet resultSet) throws SQLException {
        TariffPlanBuilder tariffPlanBuilder = new TariffPlanBuilder()
                .setId(resultSet.getInt(1))
                .setName(resultSet.getString(2));
        return tariffPlanBuilder;
    }

    @Override
    public void closeConnection() throws SQLException {
        statement.close();
        connection.close();

    }

    @Override
    public int getIDByName(String tariffName) throws SQLException {
        int id = -1;
        final String getIDByNameQuery = "SELECT id_tariff FROM tariffs WHERE name='" + tariffName + "'";
        try(ResultSet resultSet = statement.executeQuery(getIDByNameQuery)){
            if(resultSet.next()){
                id = resultSet.getInt(1);
            }
        }
        return id;
    }

    @Override
    public void addServicesToTariffPlan(int tariffID, String[] servicesIDs) throws SQLException {
        String serviceID;
        String addServicesQuery;

        for (int i = 0; i < servicesIDs.length; i++) {
            serviceID = servicesIDs[i];
            addServicesQuery = "INSERT INTO tariff_service VALUES (" + tariffID + ", " + serviceID + ")";
            statement.execute(addServicesQuery);
        }
    }

    @Override
    public void deleteTariffPlan(String tariffID) throws SQLException {
        final String deleteTariffQuery = "DELETE FROM tariffs WHERE id_tariff=" + tariffID;
        final String deleteTariffFromConnectTableQuery = "DELETE FROM tariff_service WHERE id_tariff=" + tariffID;

        statement.execute(deleteTariffFromConnectTableQuery);
        statement.execute(deleteTariffQuery);
    }
}
