package com.sergey.prykhodko.model.account;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentBuilder {

    private Integer id;
    private String paymentNumber;
    private BigDecimal paidAmount;
    private LocalDate dateOfPayment;
    private Integer invoiceId;
    private Integer accountId;

    public PaymentBuilder() {
    }

    public PaymentBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public PaymentBuilder setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
        return this;

    }

    public PaymentBuilder setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
        return this;

    }

    public PaymentBuilder setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
        return this;

    }

    public PaymentBuilder setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
        return this;

    }

    public PaymentBuilder setAccountId(Integer accountId) {
        this.accountId = accountId;
        return this;

    }

    public Payment build(){
        Payment payment = new Payment();
        payment.setId(id);
        payment.setPaymentNumber(paymentNumber);
        payment.setDateOfPayment(dateOfPayment);
        payment.setPaidAmount(paidAmount);
        payment.setInvoiceId(invoiceId);
        payment.setAccounteId(accountId);

        return payment;
    }
}
