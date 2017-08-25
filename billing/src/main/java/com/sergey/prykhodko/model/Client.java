package com.sergey.prykhodko.model;


/**
 * Created by Sergey on 25.07.2017.
 */
public class Client extends User {
    private String postAdress;
    private boolean isActive;
    private int currentBalance;


    public void makeRequestToInterconnect(){}

    public boolean isActive(){
        return isActive;
    }

    ///////////////////////////////////////////////////////////////////////
    /////////////////////////Work with balance/////////////////////////////
    //////////////////////////////////////////////////////////////////////

    public double checkCurrentBalance(){
        return currentBalance * 1.0 / 100;
    }

    public void topUpBalance(int topUpSum){
        currentBalance += topUpSum;
    }

    ///////////////////////////////////////////////////////////////////////
    /////////////////////////Work with tarifs////////////////////////////
    //////////////////////////////////////////////////////////////////////

    private void makeRequestToChangeTariffPlan(TariffPlan current, TariffPlan newOne){}
}
