package com.sergey.prykhodko.model.users;

import com.sergey.prykhodko.model.services.Service;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sergey on 25.07.2017.
 */
public class Client extends User {
    private String fullName;
    private boolean isActive;
    private TariffPlan tariffPlan;
    private Set<Service> availableServises;
    private Set<Service> activeServices;


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Client(String login, String password) {
        super(login, password);
        role = UserRole.CLIENT;

        availableServises = new HashSet<>();
        activeServices = new HashSet<>();
    }

    public String getFullName() {
        return fullName;
    }

    public TariffPlan getTariffPlan() {
        return tariffPlan;
    }

    public void setTariffPlan(TariffPlan tariffPlan) {
        this.tariffPlan = tariffPlan;
    }

    public boolean isActive() {
        return isActive;
    }

    public void makeRequestToInterconnect(){}

    public void logIn() {

    }


    @Override
    public UserRole getRole() {
        return role;
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
