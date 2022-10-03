package com.company.autopakiet.dao.impl;

import com.company.autopakiet.dao.ICarDAO;
import com.company.autopakiet.entity.Car;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

@Repository
public class ICarDAOImpl implements ICarDAO {


    private EntityManager entityManager;

    @Autowired
    public ICarDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Car> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Car> theQuery = currentSession.createQuery("from Car", Car.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Car> getByUserId(String userName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Car> theQuery = currentSession.createQuery("from Car where userName=:user_ID", Car.class);
        theQuery.setParameter("user_ID", userName);
        try {
            return theQuery.getResultList();
        }
        catch (NoResultException e){
            System.out.println("NO RESULT Exc");
            return null;
        }
    }

    @Override
    public Car getByServiceCode(int serviceCode) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Car> theQuery = currentSession.createQuery("from Car where serviceCode=:service_code", Car.class);
        theQuery.setParameter("service_code", serviceCode);
        try {
            return theQuery.getSingleResult();
        }
        catch (NonUniqueResultException e){
            System.out.println("NO RESULT Exc");
            return null;
        }
    }

    @Override
    public Car getCarBySalesCode(int saleCode) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Car> theQuery = currentSession.createQuery("from Car where saleCode=:sales_code", Car.class);
        theQuery.setParameter("sales_code", saleCode);
        try {
            return theQuery.getSingleResult();
        } catch (NoResultException e){
            System.out.println("No result found");
            return null;
        }
    }

    @Override
    public Car getById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return entityManager.find(Car.class, id);
    }

    @Transactional
    @Override
    public boolean addOrUpdate(Car car) {
        Session currentSession = entityManager.unwrap(Session.class);
        try {
            currentSession.saveOrUpdate(car);
        } catch (Exception e){
            System.out.println("Exeption");
            return false;
        }
        return true;
    }
}
