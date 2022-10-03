package com.company.autopakiet.service.impl;

import com.company.autopakiet.dao.ICarDAO;
import com.company.autopakiet.entity.Car;
import com.company.autopakiet.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ICarServiceImpl implements ICarService {

    @Autowired
    ICarDAO carDAO;

    @Override
    public List<Car> getAll() {
        return carDAO.getAll();
    }

    @Override
    public List<Car> getByUserId(String username) {
        return carDAO.getByUserId(username);
    }

    @Override
    public List<Car> getExpiredIn7DaysInsurance() {
        List<Car> cars = carDAO.getAll();
        List<Car> expired = new ArrayList<>();
        for (Car car: cars){
            if (car.getInsuranceValidity() == 7){
                expired.add(car);
            }
        }
        return expired;
    }

    @Override
    public List<Car> getExpiredIn7DaysInspection() {
        List<Car> cars = carDAO.getAll();
        List<Car> expired = new ArrayList<>();
        for (Car car: cars){
            if (car.getInspectionValidity() == 7){
                expired.add(car);
            }
        }
        return expired;
    }

    @Override
    public Car getByServiceCode(int serviceCode) {
        return carDAO.getByServiceCode(serviceCode);
    }

    @Override
    public Car getById(int id) {
        return carDAO.getById(id);
    }

    @Override
    public Car getCarBySalesCode(int saleCode) {
        return carDAO.getCarBySalesCode(saleCode);
    }

    @Override
    public boolean addOrUpdate(Car car) {
        return carDAO.addOrUpdate(car);
    }
}
