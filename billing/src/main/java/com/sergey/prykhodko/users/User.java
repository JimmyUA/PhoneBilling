package com.sergey.prykhodko.users;

/**
 *
 */
public abstract class User {
    protected String login;
    protected String password;
    protected String email;
    protected UserRole role;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public abstract void logIn();

}
