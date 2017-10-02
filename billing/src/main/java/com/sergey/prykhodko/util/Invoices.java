package com.sergey.prykhodko.util;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.model.account.Account;
import com.sergey.prykhodko.model.account.Invoice;
import com.sergey.prykhodko.services.InvoiceService;
import org.apache.log4j.Logger;

import java.util.List;

import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

public class Invoices {
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    private static final String INVOICE_PREFIX = "I-";

    public static String generateNumber(Account account) {
        Integer lastID = null;
        try {
            lastID = new InvoiceService().getLastDigitOfInvoice(account, FactoryType.MySQL);
        } catch (Exception e){
            logger.error(e);
        }
        if (lastID == null){
            lastID = 0;
        }
        return INVOICE_PREFIX + account.getId() + ++lastID;
    }
}
