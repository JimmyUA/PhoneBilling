package com.sergey.prykhodko.users;

import com.sergey.prykhodko.services.Service;
import com.sergey.prykhodko.tariffplans.TariffPlan;

import java.util.Set;

/**
 * Created by Sergey on 25.07.2017.
 */
public class Client extends User {
    private String postAdress;
    private boolean isActive;
    private Set<Service> availableServises;
    private Set<Service> activeServices;

    public Client(String login, String password) {
        super(login, password);
        role = UserRole.CLIENT;
    }

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

    public void makeRequestToActivateService(Service service){
        if (isAvailable(service)){

        }
    }

    private boolean isAvailable(Service service){
        return activeServices.contains(service);
    }

    public void makeRequestToDeactivateService(Service service){
        if (isActive(service)){

        }
    }

    private boolean isActive(Service service){
        return activeServices.contains(service);
    }

    private void makeRequestToChangeTariffPlan(TariffPlan current, TariffPlan newOne){}
}
