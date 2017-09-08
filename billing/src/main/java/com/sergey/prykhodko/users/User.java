package com.sergey.prykhodko.users;

/**
 *
 */
public abstract class User {
    protected String login;
    protected String password;
    protected String email;
    protected UserRole role = UserRole.GUEST;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public abstract void logIn();

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(UserRole role){
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public abstract UserRole getRole();

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
