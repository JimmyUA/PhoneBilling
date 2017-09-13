package com.sergey.prykhodko.managers;

import com.sergey.prykhodko.managers.commands.Command;

import javax.naming.NamingException;
import java.sql.SQLException;

public abstract class UserService {

    public static void executeCommand(Command command) throws SQLException, NamingException {
        command.execute();
    }
}
