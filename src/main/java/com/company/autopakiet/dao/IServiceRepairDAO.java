package com.company.autopakiet.dao;

import com.company.autopakiet.entity.ServiceRepair;

import java.util.List;

public interface IServiceRepairDAO {

    List<ServiceRepair> getAll ();
    ServiceRepair getById (int id);
    boolean save (ServiceRepair serviceRepair);
}
