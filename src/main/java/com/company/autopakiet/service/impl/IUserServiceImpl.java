package com.company.autopakiet.service.impl;

import com.company.autopakiet.dao.IUserDAO;
import com.company.autopakiet.entity.User;
import com.company.autopakiet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Override
    public User getByUsername(String username) {
        return userDAO.getByUsername(username);
    }

    @Override
    public boolean saveOrUpdate(User user) {
        return userDAO.saveOrUpdate(user);
    }
}
