package com.sergey.prykhodko.model.account;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Payments {
    private Integer id;
    private String paymentNumber;
    private BigDecimal paidAmount;
    private LocalDate dateOfPayment;
    private Integer invoiceId;

}
