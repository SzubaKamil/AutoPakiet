package com.company.autopakiet.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "car_inspection")
public class CarInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "results")
    @Enumerated(EnumType.STRING)
    private InsResults result;

    @Column (name = "expiration_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate expirationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "car_mileage_id")
    private CarMileage carMileage;

    public CarInspection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InsResults getResult() {
        return result;
    }

    public void setResult(InsResults result) {
        this.result = result;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public CarMileage getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(CarMileage carMileage) {
        this.carMileage = carMileage;
    }

    public enum InsResults{
        Pozytywny,
        Negatywny
    }

    @Override
    public String toString() {
        return "CarInspection{" +
                "id=" + id +
                ", result=" + result +
                ", expirationDate=" + expirationDate +
                ", carMileage=" + carMileage +
                '}';
    }
}
