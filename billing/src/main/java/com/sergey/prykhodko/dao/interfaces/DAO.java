package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.users.User;

public interface DAO {

    void storeUser(User user);
    User getUser();

}
