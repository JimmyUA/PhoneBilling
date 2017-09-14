package com.sergey.prykhodko.users;

/**
 *
 */
public abstract class User {
    protected int id;
    protected String login;
    protected String password;
    protected String email;
    protected UserRole role = UserRole.GUEST;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public abstract void logIn();

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(UserRole role){
        this.role = role;
    }

    public int getId() {
        return id;
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
