package com.sergey.prykhodko.model.tariffplans;

import com.sergey.prykhodko.model.services.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Sergey on 25.07.2017.
 */
public class TariffPlan {
    private int id;
    private String name;
    private List<Service> services;
    private BigDecimal chargePerMonth;

    public TariffPlan(String name) {
        this.name = name;
        chargePerMonth = new BigDecimal(0.000);
    }

    public TariffPlan() {
        chargePerMonth = new BigDecimal(0.000);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
        calculateTotalCharge();
    }


    public BigDecimal getChargeForMonth() {
        return chargePerMonth;
    }

    private void calculateTotalCharge() {
        for (Service service: services
             ) {
            BigDecimal chargeForService = service.getChargePerMonth();
            chargePerMonth = chargePerMonth.add(chargeForService);
        }
    }
}
