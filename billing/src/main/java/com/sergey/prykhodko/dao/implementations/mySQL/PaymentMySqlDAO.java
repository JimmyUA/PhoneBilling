package com.sergey.prykhodko.dao.implementations.mySQL;

import com.sergey.prykhodko.dao.interfaces.PaymentDAO;
import com.sergey.prykhodko.model.account.Payment;
import com.sergey.prykhodko.model.account.PaymentBuilder;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.sergey.prykhodko.connection.pool.ConnectionPool.getConnection;
import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

public class PaymentMySqlDAO implements PaymentDAO{
    private static final String GET_PAYMENTS_BY_ACCOUNT_ID = "SELECT * FROM payments WHERE id_account=?";

    private static Logger logger = Logger.getLogger(getCurrentClassName());

    private Connection connection;


    public PaymentMySqlDAO() throws SQLException, NamingException {
        connection = getConnection();
    }

    @Override
    public List<Payment> getPaymentsByAccountID(Integer id) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_PAYMENTS_BY_ACCOUNT_ID)){
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            Payment payment;
            while (resultSet.next()){
                payment = buildPayment(resultSet);
                payments.add(payment);
            }
            return payments;
        } finally {
            closeConnection();
        }
    }

    private Payment buildPayment(ResultSet resultSet) throws SQLException {
        return new PaymentBuilder()
                .setId(resultSet.getInt("id_payment"))
                .setPaymentNumber(resultSet.getString("payment_number"))
                .setDateOfPayment(LocalDate.parse(resultSet.getString("date_of_payment")))
                .setPaidAmount(new BigDecimal(resultSet.getString("paid_amount")))
                .setInvoiceId(resultSet.getInt("id_invoice"))
                .setAccountId(resultSet.getInt("id_account"))
                .build();
    }

    private void closeConnection() throws SQLException {
        connection.close();
    }
}
