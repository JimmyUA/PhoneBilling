package com.sergey.prykhodko.dao.implementations.mySQL;

import com.sergey.prykhodko.dao.interfaces.InvoiceDAO;
import com.sergey.prykhodko.model.account.Invoice;
import com.sergey.prykhodko.model.account.InvoiceBuilder;

import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.sergey.prykhodko.connection.pool.ConnectionPool.getConnection;

public class InvoiceMySqlDAO implements InvoiceDAO {
    private static final String GET_UNPAID_INVOICES = "SELECT * FROM invoices WHERE id_account=? AND is_paid=?";
    private static final String GET_INVOICES_BY_ACCOUNT_ID = "SELECT * FROM invoices WHERE id_account=?";
    private final static String GET_INVOICE_NUMBERS_BY_ACCOUNT = "SELECT invoice_number FROM invoices WHERE id_account=?";
    private final static String ADD = "INSERT INTO invoices (invoice_number, amount, id_account, due_date, is_paid) " +
            "VALUES(?, ?, ?, ?, ?)";

    private Connection connection;

    public InvoiceMySqlDAO() throws SQLException, NamingException {
        this.connection = getConnection();
    }

    @Override
    public List<Invoice> getUnpaidInvoices(Integer accountId) throws SQLException {
        List<Invoice> unpaidInvoices = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_UNPAID_INVOICES)){
            statement.setInt(1, accountId);
            statement.setBoolean(2, false);

            ResultSet resultSet = statement.executeQuery();
            Invoice invoice;
            while (resultSet.next()){
                invoice = buildInvoice(resultSet);
                unpaidInvoices.add(invoice);
            }
            return unpaidInvoices;
        } finally {
            closeConnection();
        }
    }

    private Invoice buildInvoice(ResultSet resultSet) throws SQLException {
        return new InvoiceBuilder()
                .setId(resultSet.getInt("id_invoice"))
                .setNumber(resultSet.getString("invoice_number"))
                .setAmount(resultSet.getBigDecimal("amount"))
                .setDueDate(LocalDate.parse(resultSet.getString("due_date")))
                .setPaid(resultSet.getBoolean("is_paid"))
                .build();
    }

    @Override
    public List<String> getInvoiceNumbersByAccountId(Integer accountId) throws SQLException {
        List<String> numbers = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_INVOICE_NUMBERS_BY_ACCOUNT)){
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                numbers.add(resultSet.getString("invoice_number"));
            }
        }
        return numbers;
    }

    @Override
    public void add(Invoice invoice) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(ADD)){
            statement.setString(1, invoice.getNumber());
            statement.setBigDecimal(2, invoice.getAmount());
            statement.setInt(3, invoice.getAccountId());
            LocalDate date = invoice.getDueDate();
            statement.setDate(4, Date.valueOf(date));
            statement.setBoolean(5, invoice.getPaid());

            statement.execute();
        } finally {
            closeConnection();
        }

    }

    @Override
    public List<Invoice> getInvoicesByAccountID(Integer id) throws SQLException {
        List<Invoice> unpaidInvoices = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_INVOICES_BY_ACCOUNT_ID)){
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            Invoice invoice;
            while (resultSet.next()){
                invoice = buildInvoice(resultSet);
                unpaidInvoices.add(invoice);
            }
            return unpaidInvoices;
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() throws SQLException {
        connection.close();
    }
}
