package com.sergey.prykhodko.model.users;

import com.sergey.prykhodko.exceptions.NotEnoughMoneyException;
import com.sergey.prykhodko.model.account.Account;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;

import java.math.BigDecimal;


/**
 * Created by Sergey on 25.07.2017.
 */
public class Client extends User {
    private static final String NOT_ENOUGH_MONEY_MESSAGE = "You have not enough money on your balance";

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

    public void popUpBalance(BigDecimal moneyToAdd){
        if (moneyToAdd.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException();
        }
        account.setBalance(account.getBalance().add(moneyToAdd));
    }

    public void checkAvailableServices(){}

    public void checkActiveServices(){}


    private void makeRequestToChangeTariffPlan(TariffPlan current, TariffPlan newOne){}

    public BigDecimal checkBalance() {
        return account.getBalance();
    }

    public void payAccordingTariff() {
        BigDecimal chargePerMonth = tariffPlan.getChargePerMonth();
        BigDecimal currentBalance = account.getBalance();
        if (chargePerMonth.compareTo(currentBalance) > 0){
            throw new NotEnoughMoneyException(NOT_ENOUGH_MONEY_MESSAGE);
        }
        account.setBalance(currentBalance.subtract(chargePerMonth));
    }

    public void pay(BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException();
        }
        BigDecimal currentBalance = account.getBalance();
        if (money.compareTo(currentBalance) > 0){
            throw new NotEnoughMoneyException(NOT_ENOUGH_MONEY_MESSAGE);
        }
        account.setBalance(currentBalance.subtract(money));
    }
}
