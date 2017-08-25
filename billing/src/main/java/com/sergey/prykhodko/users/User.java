package com.sergey.prykhodko.users;

/**
 *
 */
public abstract class User {
    private String login;
    private String password;
    private String email;

    public abstract void logIn();

}
