package com.company.autopakiet.dao.impl;

import com.company.autopakiet.dao.IServiceRepairDAO;
import com.company.autopakiet.entity.ServiceRepair;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class IServiceRepairDAOImpl implements IServiceRepairDAO {

    private EntityManager entityManager;

    public IServiceRepairDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ServiceRepair> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<ServiceRepair> theQuery = currentSession.createQuery("from ServiceRepair", ServiceRepair.class);
        return theQuery.getResultList();
    }

    @Override
    public ServiceRepair getById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return entityManager.find(ServiceRepair.class, id);
    }

    @Override
    public boolean save(ServiceRepair serviceRepair) {
        Session currentSession = entityManager.unwrap(Session.class);
        try {
            currentSession.saveOrUpdate(serviceRepair);
        } catch (Exception e){
            System.out.println("Exeption");
            return false;
        }
        return true;
    }
}
