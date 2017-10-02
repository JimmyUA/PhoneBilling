package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.model.account.Invoice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface InvoiceDAO extends DAO{

    List<Invoice> getUnpaidInvoices(Integer accountId) throws SQLException;

    void add(Invoice invoice) throws SQLException;

    List<String> getInvoiceNumbersByAccountId(Integer id) throws SQLException;

    List<Invoice> getInvoicesByAccountID(Integer id) throws SQLException;

    void update(Invoice invoice) throws SQLException;

    void setConnection(Connection transactionConnection) throws SQLException;
}
