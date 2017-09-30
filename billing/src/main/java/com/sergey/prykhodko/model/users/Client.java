package com.sergey.prykhodko.model.users;

import com.sergey.prykhodko.model.account.Account;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;


/**
 * Created by Sergey on 25.07.2017.
 */
public class Client extends User {
    private String fullName;
    private boolean isActive;
    private TariffPlan tariffPlan;
    private Account account;


    public TariffPlan getTariffPlan() {
        return tariffPlan;
    }

    public void setTariffPlan(TariffPlan tariffPlan) {
        this.tariffPlan = tariffPlan;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Client(String login, String password) {
        super(login, password);
        role = UserRole.CLIENT;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public UserRole getRole() {
        return role;
    }

    public void checkAvailableServices(){}

    public void checkActiveServices(){}


    private void makeRequestToChangeTariffPlan(TariffPlan current, TariffPlan newOne){}
}
