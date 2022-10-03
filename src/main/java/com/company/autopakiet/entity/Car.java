package com.company.autopakiet.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "producer")
    private String producer;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column (name = "service_code")
    private int serviceCode;

    @Column (name = "sales_code")
    private int saleCode;

    @Column(name = "user_ID")
    private String userName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "active_Tire_id")
    private Tire activeTire;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "lpg_id")
    private Lpg lpg;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private List<CarMileage> carMileages;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private List<CarInspection> carInspections;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private List<Insurance> insurances;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private List<RepairHistory> repairHistories;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private List<Tire> tires;

    public Car() {
        // random service code
        this.serviceCode = new Random().nextInt(Integer.MAX_VALUE);
        this.tires = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    public int getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(int saleCode) {
        this.saleCode = saleCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Tire getActiveTire() {
        return activeTire;
    }

    public void setActiveTire(Tire activeTire) {
        addTire(activeTire);
        this.activeTire = activeTire;
    }

    public Lpg getLpg() {
        return lpg;
    }

    public void setLpg(Lpg lpg) {
        this.lpg = lpg;
    }

    public List<CarMileage> getCarMileages() {
        return carMileages;
    }

    public void setCarMileages(List<CarMileage> carMileages) {
        this.carMileages = carMileages;
    }

    public List<CarInspection> getCarInspections() {
        return carInspections;
    }

    public void setCarInspections(List<CarInspection> carInspections) {
        this.carInspections = carInspections;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    public List<RepairHistory> getRepairHistories() {
        return repairHistories;
    }

    public void setRepairHistories(List<RepairHistory> repairHistories) {
        this.repairHistories = repairHistories;
    }

    public List<Tire> getTires() {
        return tires;
    }

    public void setTires(List<Tire> tires) {
        this.tires = tires;
    }

    public CarMileage getLastMileage (){
        if (carMileages.size()<1){
            CarMileage carMileage = new CarMileage();
            carMileage.setValue(0);
            return carMileage;
        }
        return carMileages.get(carMileages.size()-1);
    }

    public long getInsuranceValidity (){
        if (insurances.size()>0){
            LocalDate endDate = insurances.get(insurances.size() - 1).getEndDate();
            return ChronoUnit.DAYS.between(LocalDate.now(), endDate);
        }
        return 0;
    }

    public long getInspectionValidity (){
        if (carInspections.size()>0){
            LocalDate endDate = carInspections.get(carInspections.size()-1).getExpirationDate();
            return ChronoUnit.DAYS.between(LocalDate.now(), endDate);
        }
        return 0;
    }

    public void addTire (Tire tire){
        if (!tires.contains(tire)){
            tires.add(tire);
        }
    }

    public void addCarMileage (CarMileage carMileage){
        if (carMileages==null){
            carMileages = new ArrayList<>();
        }
        carMileages.add(carMileage);
    }

    public void addCarInsurance (Insurance insurance){
        if (insurances==null){
            insurances = new ArrayList<>();
        }
        insurances.add(insurance);
    }

    public void addCarInspection(CarInspection carInspection){
        if (carInspections == null){
            carInspections = new ArrayList<>();
        }
        carInspections.add(carInspection);
    }

    public void addRepairHistory(RepairHistory repairHistory) {
        if (repairHistory == null){
            repairHistories = new ArrayList<>();
        }
        repairHistories.add(repairHistory);
    }

    public enum FuelType {
        PB,
        ON,
        HYBRYDA,
        LPG
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", fuelType=" + fuelType +
                ", serviceCode=" + serviceCode +
                ", saleCode=" + saleCode +
                ", activeTire=" + activeTire +
                ", lpg=" + lpg +
                ", carMileages=" + carMileages +
                ", carInspections=" + carInspections +
                ", insurances=" + insurances +
                ", repairHistories=" + repairHistories +
                ", tires=" + tires +
                '}';
    }
}
