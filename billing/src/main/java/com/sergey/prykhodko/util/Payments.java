package com.sergey.prykhodko.util;

import com.sergey.prykhodko.model.account.Invoice;

public class Payments {
        private static final int BEGIN_DIGITS_INDEX = 2;
        private static final String PAYMENT_PREFIX = "P-";

    public static String generatePaymentNumber(Invoice invoice) {
        String invoiceNumber = invoice.getNumber();
        String digitPart = invoiceNumber.substring(BEGIN_DIGITS_INDEX);
        return PAYMENT_PREFIX + digitPart;
    }
}
