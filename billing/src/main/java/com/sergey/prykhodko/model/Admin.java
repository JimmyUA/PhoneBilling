package com.sergey.prykhodko.model.users;

import com.sergey.prykhodko.model.services.Service;
import com.sergey.prykhodko.services.Service;

public class Admin extends User{

    public void logIn() {

    }

    public void interconnectClient(Client client){}

    public void activateServiceForClient(Client client, Service service){}

    public void deactivateServiceForClient(Client client, Service service){}


}
