package com.sergey.prykhodko.model.account;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceBuilder {
    private Integer id;
    private String number;
    private BigDecimal amount;
    private LocalDate dueDate;
    private Integer accountId;
    private Boolean isPaid;

    public InvoiceBuilder() {
    }


    public InvoiceBuilder setAccountId(Integer accountId) {
        this.accountId = accountId;
        return this;
    }

    public InvoiceBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public InvoiceBuilder setNumber(String number) {
        this.number = number;
        return this;

    }

    public InvoiceBuilder setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;

    }

    public InvoiceBuilder setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;

    }

    public InvoiceBuilder setPaid(Boolean paid) {
        isPaid = paid;
        return this;
    }

    public Invoice build(){
        Invoice invoice = new Invoice();
        invoice.setId(id);
        invoice.setNumber(number);
        invoice.setAmount(amount);
        invoice.setDueDate(dueDate);
        invoice.setPaid(isPaid);
        invoice.setAccountId(accountId);
        return invoice;
    }
}
