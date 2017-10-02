package com.sergey.prykhodko.model.account;

import java.math.BigDecimal;
import java.util.List;

public class Account {
    private Integer accountId;
    private String accountNumber;
    private List<Invoice> invoices;
    private List<Payment> payments;
    private BigDecimal balance;

    public Integer getId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
