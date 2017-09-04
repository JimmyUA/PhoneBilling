package com.sergey.prykhodko.users;

import com.sergey.prykhodko.services.Service;

public class Admin extends User{

    public Admin(String login, String password) {
        super(login, password);
    }

    public void logIn() {

    }

    public void interconnectClient(Client client){}

    public void activateServiceForClient(Client client, Service service){}

    public void deactivateServiceForClient(Client client, Service service){}


}
