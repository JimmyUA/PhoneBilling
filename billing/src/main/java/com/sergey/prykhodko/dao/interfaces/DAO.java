package com.sergey.prykhodko.dao.interfaces;

import java.sql.SQLException;
import java.util.Set;

public interface DAO {
    Set<String> getLogins() throws SQLException;
}
