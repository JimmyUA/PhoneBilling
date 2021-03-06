package com.sergey.prykhodko.services.commands;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.services.UsersService;
import com.sergey.prykhodko.model.users.Client;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;

import static com.sergey.prykhodko.util.ClassName.getCurrentClassName;

public class RegisterClient implements Command {
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    private Client client;

    public RegisterClient(Client client) {
        this.client = client;
    }

    public RegisterClient() {
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void execute() throws SQLException, NamingException {
        new UsersService().addUserToDB(client, FactoryType.MySQL);
        logger.info(client.getLogin() + " is registered as a client");
    }
}
