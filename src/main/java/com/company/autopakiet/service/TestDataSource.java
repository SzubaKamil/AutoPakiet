package com.company.autopakiet.service;

import com.company.autopakiet.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public  class TestDataSource {

    public static List<ServiceRepair> serviceRepairs (){
        List<ServiceRepair> sR = new ArrayList<>();

        ServiceRepair serviceRepair = new ServiceRepair();
        serviceRepair.setPrice(150);
        serviceRepair.setName("Wymana klocków");
        serviceRepair.setId(1);

        ServiceRepair serviceRepair2 = new ServiceRepair();
        serviceRepair2.setPrice(250);
        serviceRepair2.setName("Wymiana Tarcz");
        serviceRepair2.setId(2);

        ServiceRepair serviceRepair3= new ServiceRepair();
        serviceRepair3.setPrice(1000);
        serviceRepair3.setName("Wymiana Silnika");
        serviceRepair3.setId(3);

        ServiceRepair serviceRepair4= new ServiceRepair();
        serviceRepair4.setPrice(10000);
        serviceRepair4.setName("Naprawa nadwozia");
        serviceRepair4.setId(4);

        sR.add(serviceRepair);
        sR.add(serviceRepair2);
        sR.add(serviceRepair3);
        sR.add(serviceRepair4);

        return sR;
    }

    public static List<Tire> tireList (){
        Tire tire = new Tire();
        tire.setProducer("Debica");
        tire.setSeason(Tire.TireSeason.Letnie);
        tire.setSize(15);
        tire.setId(1);

        Tire tire2 = new Tire();
        tire2.setProducer("Michelean");
        tire2.setSeason(Tire.TireSeason.Zimowe);
        tire2.setSize(14);
        tire2.setId(2);


        List<Tire> tireList = new ArrayList<>();
        tireList.add(tire);
        tireList.add(tire2);
        return tireList;
    }

    public  static List<Car> carList (){
        Car car = new Car();
        car.setId(1);
        car.setProducer("Opel");
        car.setModel("Insignia");
        car.setServiceCode(1526658);
        car.setCarMileages(carMileageList());
        car.setCarInspections(carInspections());
        Lpg lpg = new Lpg();
        lpg.setCylinderValidity(LocalDate.now());
        lpg.setInstallationYear(2025);

        car.setLpg(lpg);

        Car car1 = new Car();
        car1.setId(2);
        car1.setProducer("Honda");
        car1.setModel("CRV");
        car1.setServiceCode(99999999);
        car1.setCarMileages(carMileageList());
        car1.setCarInspections(carInspections());

        List<Car> cars = new ArrayList<>();
        cars.add(car);
        cars.add(car1);

        return cars;
    }

    public static List<CarMileage> carMileageList (){
        List<CarMileage> carMileageList = new ArrayList<>();

        CarMileage carMileage = new CarMileage();
        carMileage.setValue(15200);
        carMileageList.add(carMileage);

        CarMileage carMileage2 = new CarMileage();
        carMileage2.setValue(18200);
        carMileageList.add(carMileage2);

        CarMileage carMileage3 = new CarMileage();
        carMileage3.setValue(28200);
        carMileageList.add(carMileage3);

        return carMileageList;
    }

    public static List<CarInspection> carInspections (){
        CarInspection carInspection = new CarInspection();
        carInspection.setResult(CarInspection.InsResults.Pozytywny);
        carInspection.setCarMileage(carMileageList().get(0));
        carInspection.setExpirationDate(LocalDate.now());

        CarInspection carInspection2 = new CarInspection();
        carInspection2.setResult(CarInspection.InsResults.Negatywny);
        carInspection2.setCarMileage(carMileageList().get(1));
        carInspection2.setExpirationDate(LocalDate.now());

        List<CarInspection> carInspections = new ArrayList<>();
        carInspections.add(carInspection);
        carInspections.add(carInspection2);

        return carInspections;
    }

    public static List<RepairHistory> repairHistories (){
        RepairHistory repairHistory = new RepairHistory();
        repairHistory.setDate(LocalDate.now());
        repairHistory.setServiceRepair(serviceRepairs().get(0));

        RepairHistory repairHistory2 = new RepairHistory();
        repairHistory2.setDate(LocalDate.now());
        repairHistory2.setServiceRepair(serviceRepairs().get(1));

        List<RepairHistory> repairHistories = new ArrayList<>();
        repairHistories.add(repairHistory);
        repairHistories.add(repairHistory2);

        return repairHistories;
    }

}
