package com.company.autopakiet.entity;

import javax.persistence.*;

@Entity
@Table(name = "tire")
public class Tire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "producer")
    private String producer;

    @Column(name = "size")
    private int size;

    @Column(name = "season")
    @Enumerated(EnumType.STRING)
    private TireSeason season;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "car_id")
    private Car car;

    public Tire() {
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public TireSeason getSeason() {
        return season;
    }

    public void setSeason(TireSeason season) {
        this.season = season;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public enum TireSeason {
        Letnie,
        Zimowe,
        Całoroczne
    }

    @Override
    public String toString() {
        return "Tire{" +
                "id=" + id +
                ", producer='" + producer + '\'' +
                ", size=" + size +
                ", season=" + season +
                '}';
    }
}
