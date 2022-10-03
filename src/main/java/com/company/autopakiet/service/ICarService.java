package com.company.autopakiet.service;

import com.company.autopakiet.entity.Car;

import java.util.List;

public  interface ICarService {

    List<Car> getAll();
    List<Car> getByUserId(String username);
    List<Car> getExpiredIn7DaysInsurance ();
    List<Car> getExpiredIn7DaysInspection ();
    Car getByServiceCode(int serviceCode);
    Car getById (int id);
    Car getCarBySalesCode(int saleCode);
    boolean addOrUpdate (Car car);
}
