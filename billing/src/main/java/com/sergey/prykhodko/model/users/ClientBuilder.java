package com.sergey.prykhodko.model.users;


public class ClientBuilder {
    private Integer id = 0;
    private String login;
    private String password;
    private String email;
    private String fullName;
    private boolean isActive;
    private Integer tariffPlanId = 0;
    private Integer accountId = 0;

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

    public ClientBuilder setTariffPlanId(Integer tariffPlanId) {
        this.tariffPlanId = tariffPlanId;
        return this;
    }

    public ClientBuilder setAccountId(Integer accountId) {
        this.accountId = accountId;
        return this;
    }


    public Client build(){
        Client client = new Client(login, password);
        client.setId(id);
        client.setEmail(email);
        client.setFullName(fullName);
        client.setActive(isActive);
        client.setTariffPlanId(tariffPlanId);
        client.setAccountId(accountId);
        return client;
    }

}
