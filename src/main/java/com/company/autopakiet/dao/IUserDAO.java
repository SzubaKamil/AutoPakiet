package com.company.autopakiet.dao;

import com.company.autopakiet.entity.User;

public interface IUserDAO {

    User getByUsername (String username);
    String geEmailByName (String username);
    boolean saveOrUpdate (User user);
}
