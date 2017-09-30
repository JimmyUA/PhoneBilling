package com.sergey.prykhodko.util;

import com.sergey.prykhodko.dao.factories.FactoryType;
import com.sergey.prykhodko.services.AccountService;

public class Accounts {
    private final static Integer START_ID = 10000;
    public static Integer generateId() {
        Integer lastID;
        try {
            lastID = new AccountService().getLastId(FactoryType.MySQL);
        } catch (Exception e){
            return START_ID;
        }
        return ++lastID;
    }
}
