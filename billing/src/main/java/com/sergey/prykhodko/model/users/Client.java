package com.sergey.prykhodko.model.users;

import com.sergey.prykhodko.model.account.Account;
import com.sergey.prykhodko.model.services.Service;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sergey on 25.07.2017.
 */
public class Client extends User {
    private String fullName;
    private boolean isActive;
    private Integer tariffPlanId;
    private Integer accountId;

    public Integer getTariffPlanId() {
        return tariffPlanId;
    }

    public void setTariffPlanId(Integer tariffPlanId) {
        this.tariffPlanId = tariffPlanId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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
