package com.sergey.prykhodko.managers;

import com.sergey.prykhodko.model.services.Service;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;


import java.util.List;

public class TariffPlanBuilder {
    private int id;
    private String name;
    private List<Service> services;

    public TariffPlanBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public TariffPlanBuilder setName(String name){
        this.name = name;
        return this;
    }

    public TariffPlanBuilder setServises(List<Service> services){
        this.services = services;
        return this;
    }

    public TariffPlan build(){
        TariffPlan goal = new TariffPlan(name);
        goal.setId(id);
        goal.setServices(services);
        return goal;
    }

    public int getID() {
        return id;
    }
}
