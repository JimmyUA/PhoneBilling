package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.model.account.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends DAO{
    List<Payment> getPaymentsByAccountID(Integer id) throws SQLException;
}
