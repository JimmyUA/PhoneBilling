package com.sergey.prykhodko.model.account;

import java.math.BigDecimal;
import java.util.List;

public class Account {
    private List<Invoice> invoices;
    private List<Payments> payments;
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }
}
