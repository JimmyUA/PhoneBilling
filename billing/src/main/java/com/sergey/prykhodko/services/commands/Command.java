package com.sergey.prykhodko.services.commands;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface Command {
    void execute() throws SQLException, NamingException;
}
