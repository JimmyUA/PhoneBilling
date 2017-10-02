package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.PaymentDAO;
import com.sergey.prykhodko.model.account.Invoice;
import com.sergey.prykhodko.model.account.Payment;
import com.sergey.prykhodko.model.account.PaymentBuilder;
import com.sergey.prykhodko.util.Payments;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PaymentService implements Service{

    public void generatePayments(List<Invoice> unpaidInvoices, FactoryType factoryType, Connection transactionConnection) throws SQLException, NamingException {
        for (Invoice invoice : unpaidInvoices
             ) {
            createNewPayment(invoice, factoryType, transactionConnection);
        }
    }

    private void createNewPayment(Invoice invoice, FactoryType factoryType, Connection transactionConnection) throws SQLException, NamingException {
        Payment payment = buildPayment(invoice);
        addToDB(payment, factoryType, transactionConnection);
    }

    private void addToDB(Payment payment, FactoryType factoryType, Connection transactionConnection) throws SQLException, NamingException {
        PaymentDAO paymentDAO = getPaymentDAO(factoryType);
        paymentDAO.setConnection(transactionConnection);
        paymentDAO.add(payment);
    }

    private PaymentDAO getPaymentDAO(FactoryType factoryType) throws SQLException, NamingException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(factoryType);
        return daoFactory.getPaymentDAO();
    }

    private Payment buildPayment(Invoice invoice) {
        return new PaymentBuilder()
                .setPaymentNumber(Payments.generatePaymentNumber(invoice))
                .setPaidAmount(invoice.getAmount())
                .setDateOfPayment(LocalDate.now())
                .setInvoiceId(invoice.getId())
                .setAccountId(invoice.getAccountId())
                .build();
    }
}
