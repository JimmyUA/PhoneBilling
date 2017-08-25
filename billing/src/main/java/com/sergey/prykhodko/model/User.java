package com.sergey.prykhodko.model.users;

/**
 *
 */
public abstract class User {
    private String login;
    private String password;
    private String email;

    public abstract void logIn();

}
