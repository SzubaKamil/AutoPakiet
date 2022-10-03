package com.company.autopakiet.service;

import com.company.autopakiet.entity.ServiceRepair;

import java.util.List;

public interface IServiceRepairService {

    public List<ServiceRepair> getAll();
    ServiceRepair getById(int id);
    boolean save (ServiceRepair serviceRepair);
}
