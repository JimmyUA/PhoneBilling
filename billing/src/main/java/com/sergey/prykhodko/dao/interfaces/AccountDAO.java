package com.sergey.prykhodko.dao.interfaces;

import java.sql.SQLException;

public interface AccountDAO{
    Integer getLastID() throws SQLException;

    void add(Integer accountId) throws SQLException;
}

