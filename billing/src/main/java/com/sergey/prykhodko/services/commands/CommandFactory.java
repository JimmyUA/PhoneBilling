package com.sergey.prykhodko.managers.commands;

public interface CommandFactory {

   static Command getCommand(CommandType commandType){
        switch (commandType){
            case LOGIN_COMMAND:
                return new LoginCommand();
            case REGISTER_CLIENT:
                return new RegisterClient();
            case CHANGE_CLIENT_STATUS:
                return new ChangeClientStatus();
                default:
                    return null;
        }
    }
}
