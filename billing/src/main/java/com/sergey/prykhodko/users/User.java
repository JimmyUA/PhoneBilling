package com.sergey.prykhodko.users;

/**
 *
 */
public abstract class User {
    private String login;
    private String password;
    private String email;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public abstract void logIn();

}
