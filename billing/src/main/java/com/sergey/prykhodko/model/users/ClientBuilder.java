package com.sergey.prykhodko.model.users;


import com.sergey.prykhodko.model.account.Account;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;

public class ClientBuilder {
    private Integer id = 0;
    private String login;
    private String password;
    private String email;
    private String fullName;
    private boolean isActive;
    private TariffPlan tariffPlan;
    private Account account;

    public ClientBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ClientBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public ClientBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public ClientBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public ClientBuilder setActive(boolean active) {
        isActive = active;
        return this;
    }

    public ClientBuilder setTariffPlan(TariffPlan tariffPlan) {
        this.tariffPlan = tariffPlan;
        return this;
    }

    public ClientBuilder setAccount(Account account) {
        this.account = account;
        return this;
    }


    public Client build(){
        Client client = new Client(login, password);
        client.setId(id);
        client.setEmail(email);
        client.setFullName(fullName);
        client.setActive(isActive);
        client.setTariffPlan(tariffPlan);
        client.setAccount(account);
        return client;
    }

}
