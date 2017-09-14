package com.sergey.prykhodko.managers.commands;

import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.managers.UsersManager;
import com.sergey.prykhodko.model.users.Client;

import javax.naming.NamingException;
import java.sql.SQLException;

public class ChangeClientStatus implements Command {

    private Client client;

    public ChangeClientStatus(Client client) {
        this.client = client;
    }

    @Override
    public void execute() throws SQLException, NamingException {
        changeClientStatus(client);
        new UsersManager().updateUser(client, FactoryType.MySQL);
    }

    private void changeClientStatus(Client client) {
        client.setActive(!client.isActive());
    }
}
