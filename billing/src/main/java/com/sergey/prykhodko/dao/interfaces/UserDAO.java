package com.sergey.prykhodko.dao.interfaces;

import com.sergey.prykhodko.users.User;

import java.sql.SQLException;

public interface UserDAO extends DAO {
    void storeUser(User user);
    User getUser(String login) throws SQLException;
}
