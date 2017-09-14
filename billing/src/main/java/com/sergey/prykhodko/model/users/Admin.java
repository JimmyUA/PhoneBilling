package com.sergey.prykhodko.model.users;

import com.sergey.prykhodko.model.services.Service;

public class Admin extends User{

    public Admin(String login, String password) {
        super(login, password);
        role = UserRole.ADMIN;
    }

    public void logIn() {

    }


    @Override
    public UserRole getRole() {
        return role;
    }

    public void interconnectClient(Client client){}

    public void activateServiceForClient(Client client, Service service){}

    public void deactivateServiceForClient(Client client, Service service){}


}
