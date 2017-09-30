package com.sergey.prykhodko.services.commands;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.services.UsersService;
import com.sergey.prykhodko.model.users.Client;

import javax.naming.NamingException;
import java.sql.SQLException;

public class ChangeClientStatus implements Command {

    private Client client;

    public ChangeClientStatus(Client client) {
        this.client = client;
    }

    public ChangeClientStatus() {
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void execute() throws SQLException, NamingException {
        changeClientStatus(client);
        new UsersService().updateUser(client, FactoryType.MySQL);
    }

    private void changeClientStatus(Client client) {
        client.setActive(!client.isActive());
    }
}
