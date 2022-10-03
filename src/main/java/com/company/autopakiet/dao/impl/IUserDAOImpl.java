package com.company.autopakiet.dao.impl;

import com.company.autopakiet.dao.IUserDAO;
import com.company.autopakiet.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class IUserDAOImpl implements IUserDAO {

    private EntityManager entityManager;

    @Autowired
    public IUserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User getByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        return entityManager.find(User.class, username);
    }

    @Override
    public String geEmailByName(String username) {
        return getByUsername(username).getEmail();
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        try {
            currentSession.saveOrUpdate(user);
            entityManager.createNativeQuery("INSERT INTO authorities (username, authority) VALUES (?,?)")
                    .setParameter(1, user.getUsername())
                    .setParameter(2, user.getAuthority())
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception");
            return false;
        }
        return true;
    }
}
