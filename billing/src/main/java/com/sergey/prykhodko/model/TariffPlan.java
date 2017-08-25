package com.sergey.prykhodko.model.tariffplans;

import com.sergey.prykhodko.model.services.Service;

import java.util.Set;

/**
 * Created by Sergey on 25.07.2017.
 */
public class TariffPlan {
    private int id;
    private String name;
    private Set<Service> services;
}
