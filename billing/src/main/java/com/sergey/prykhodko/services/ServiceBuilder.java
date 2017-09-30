package com.sergey.prykhodko.services;

import com.sergey.prykhodko.model.services.Service;

import java.math.BigDecimal;

public class ServiceBuilder {
    private int id;
    private String name;
    private BigDecimal chargePerMonth;

    public ServiceBuilder setID(int id) {
        this.id = id;
        return this;
    }

    public ServiceBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ServiceBuilder setCharge(BigDecimal chargePerMonth) {
        this.chargePerMonth = chargePerMonth;
        return this;
    }

    public Service build(){
        Service service = new Service();

        service.setId(id);
        service.setName(name);
        service.setChargePerMonth(chargePerMonth);

        return service;
    }
}
