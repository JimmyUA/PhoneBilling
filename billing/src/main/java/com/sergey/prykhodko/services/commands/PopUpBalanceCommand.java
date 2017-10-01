package com.sergey.prykhodko.services.commands;

import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.services.UsersService;

import javax.naming.NamingException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class PopUpBalanceCommand implements Command {
    private BigDecimal amount;
    private Client client;

        public PopUpBalanceCommand(Client client, BigDecimal amount) {
            this.amount = amount;
            this.client = client;
        }

        @Override
    public void execute() throws SQLException, NamingException {
        new UsersService().popUpBalance(client, amount);
    }
}
