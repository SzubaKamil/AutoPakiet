package com.company.autopakiet.dao;

import com.company.autopakiet.entity.Car;

import java.util.List;

public interface ICarDAO {

    List<Car> getAll();
    List<Car> getByUserId (String userName);
    Car getByServiceCode (int serviceCode);
    Car getById (int id);
    Car getCarBySalesCode (int saleCode);
    boolean addOrUpdate (Car car);


}
