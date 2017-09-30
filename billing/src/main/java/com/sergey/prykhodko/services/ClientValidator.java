package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.model.users.Client;
import com.sergey.prykhodko.model.users.UserRole;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientValidator implements Validator<Client> {
    private static Logger logger = Logger.getLogger(ClientValidator.class);

    @Override
    public boolean validate(Client unitToValidate) {
        Client client = unitToValidate;
        if (notUniqueLogin(client.getLogin())) {
            logger.error("Login - \"" + client.getLogin() + "\" is not unique");
            return false;
        } else if (passwordNotMatchPattern(client.getPassword())) {
            logger.error("Password - \"" + client.getPassword() + "\" is not valid");
            return false;
        } else if (emailNotMatchPattern(client.getEmail())) {
            logger.error("Email - \"" + client.getEmail() + "\" is not valid");
            return false;
        } else if (fullNameLessTwoWords(client.getFullName())) {
            logger.error("Full name - \"" + client.getFullName() + "\" is not valid");
            return false;
        }
        else if (client.isActive()){
            logger.error("Status is not valid");
            return false;
        }
        return true;
    }



    private boolean notUniqueLogin(String clientLogin) {
        DAOFactory factory = DAOFactory.getDAOFactory(FactoryType.MySQL);
        Set<String> logins;
        try {
            UserDAO userDAO = factory.getUserDAO(UserRole.CLIENT);
            logins = userDAO.getLogins();
        } catch (SQLException | NamingException e){
            logger.error(e);
            return true;
        }
        for (String login : logins
             ) {
            if (clientLogin.equals(login)){
                return true;
            }
        }
        return false;
    }

    private boolean passwordNotMatchPattern(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Matcher matcher = pattern.matcher(password);

        return !matcher.find();
    }

    private boolean emailNotMatchPattern(String email) {
        Pattern pattern = Pattern.compile("^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$");
        Matcher matcher = pattern.matcher(email);

        return !matcher.find();
    }

    private boolean fullNameLessTwoWords(String fullName) {
        return fullName.split(" ").length < 2;
    }
}
