package com.company.autopakiet.service;

import com.company.autopakiet.entity.User;

public interface IUserService {

    User getByUsername (String username);
    boolean saveOrUpdate(User user);
}
