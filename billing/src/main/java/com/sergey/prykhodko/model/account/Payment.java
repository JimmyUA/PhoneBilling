package com.sergey.prykhodko.model.account;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Payment {
    private Integer id;
    private String paymentNumber;
    private BigDecimal paidAmount;
    private LocalDate dateOfPayment;
    private Integer invoiceId;
    private Integer accounteId;

    public Payment() {
    }

    public Integer getAccounteId() {
        return accounteId;
    }

    public void setAccounteId(Integer accounteId) {
        this.accounteId = accounteId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }
}
