package com.sergey.prykhodko.dao.implementations.mySQL;

import com.sergey.prykhodko.dao.interfaces.AccountDAO;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.*;

import static com.sergey.prykhodko.connection.pool.ConnectionPool.getConnection;
import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

public class AccountMySqlDAO implements AccountDAO {
    private final static String GET_LAST_ID = "SELECT MAX(id_account) FROM accounts";
    private final static String ADD = "INSERT INTO accounts (id_account, account_number, balance) " +
            "VALUES(?,?, 0.000)";
    private final static String ACCOUNT_ID_LABEL = "id_account";
    private final static String ACCOUNT_NUMBER_PREFIX = "AC-";
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    private Connection connection;

    public AccountMySqlDAO() throws SQLException, NamingException {
        connection = getConnection();
    }

    @Override
    public Integer getLastID() throws SQLException {
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(GET_LAST_ID);
            resultSet.next();

            return resultSet.getInt(1);
        }finally {
            connection.close();
        }
    }

    @Override
    public void add(Integer accountId) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(ADD)){
            statement.setInt(1, accountId);
            statement.setString(2, ACCOUNT_NUMBER_PREFIX + accountId);
            statement.execute();
        }finally {
            connection.close();
        }
    }
}
