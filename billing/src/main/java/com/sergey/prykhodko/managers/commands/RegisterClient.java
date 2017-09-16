package com.sergey.prykhodko.managers.commands;

import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.managers.UsersManager;
import com.sergey.prykhodko.model.users.Client;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;

import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;

public class RegisterClient implements Command {
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    private Client client;

    public RegisterClient(Client client) {
        this.client = client;
    }
    @Override
    public void execute() throws SQLException, NamingException {
        new UsersManager().addUserToDB(client, FactoryType.MySQL);
        logger.info(client.getLogin() + " is registered as a client");
    }
}
