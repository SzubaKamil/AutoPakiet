package com.company.autopakiet.service.impl;

import com.company.autopakiet.dao.IServiceRepairDAO;
import com.company.autopakiet.entity.ServiceRepair;
import com.company.autopakiet.service.IServiceRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IServiceRepairServiceImpl implements IServiceRepairService {

    @Autowired
    IServiceRepairDAO serviceRepairDAO;

    @Override
    public List<ServiceRepair> getAll() {
        return serviceRepairDAO.getAll();
    }

    @Override
    public ServiceRepair getById(int id) {
        return serviceRepairDAO.getById(id);
    }

    @Override
    public boolean save(ServiceRepair serviceRepair) {
        return serviceRepairDAO.save(serviceRepair);
    }
}
