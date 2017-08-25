package com.sergey.prykhodko.model.users;

import com.sergey.prykhodko.model.tariffplans.TariffPlan;

/**
 * Created by Sergey on 25.07.2017.
 */
public class Client extends User {
    private int id;
    private String postAdress;
    private boolean isActive;


    public void makeRequestToInterconnect(){}

    public void logIn() {

    }

    ///////////////////////////////////////////////////////////////////////
    /////////////////////////Work with balance/////////////////////////////
    //////////////////////////////////////////////////////////////////////

    public void checkCurrentBalance(){}

    public void topUpBalance(int topUpSum){}

    ///////////////////////////////////////////////////////////////////////
    /////////////////////////Work with services////////////////////////////
    //////////////////////////////////////////////////////////////////////

    public void checkAvailableServices(){}

    public void checkActiveServices(){}

    private void makeRequestToChangeTariffPlan(TariffPlan current, TariffPlan newOne){}
}
