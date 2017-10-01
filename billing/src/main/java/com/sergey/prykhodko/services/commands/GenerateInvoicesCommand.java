package com.sergey.prykhodko.services.commands;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.services.InvoiceService;

import javax.naming.NamingException;
import java.sql.SQLException;

public class GenerateInvoicesCommand implements Command {
    @Override
    public void execute() throws SQLException, NamingException {
        new InvoiceService().generateInvoices(FactoryType.MySQL);
    }
}
