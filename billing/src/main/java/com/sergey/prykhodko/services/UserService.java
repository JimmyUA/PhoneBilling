package com.sergey.prykhodko.services;

import com.sergey.prykhodko.services.commands.Command;

import javax.naming.NamingException;
import java.sql.SQLException;

public abstract class UserService {

    public static void executeCommand(Command command) throws SQLException, NamingException {
        command.execute();
    }
}
