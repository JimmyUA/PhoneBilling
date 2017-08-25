package com.sergey.prykhodko.model;

import com.sergey.prykhodko.model.Service;

import java.util.Set;

/**
 * Created by Sergey on 25.07.2017.
 */
public class TariffPlan {
    private int id;
    private String name;
    private int monthlyPayment;
    private Set<Service> services;
}
