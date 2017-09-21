package com.sergey.prykhodko.model.services;

import java.math.BigDecimal;

/**
 * Created by Sergey on 25.07.2017.
 */
public class Service {
    private int id;
    private String name;
    private BigDecimal chargePerMonth;




    public BigDecimal getChargePerMonth() {
        return chargePerMonth;
    }

    public int getID() {
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

    public void setChargePerMonth(BigDecimal chargePerMonth) {
        this.chargePerMonth = chargePerMonth;
    }


}
