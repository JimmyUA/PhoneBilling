package com.sergey.prykhodko.users;

public class ClientBuilder {
    private String login;
    private String password;
    private String email;
    private String fullName;
    private boolean isActive;

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

    public Client build(){
        Client client = new Client(login, password);
        client.setEmail(email);
        client.setFullName(fullName);
        client.setActive(isActive);
        return client;
    }
}
