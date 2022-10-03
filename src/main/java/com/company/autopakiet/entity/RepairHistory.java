package com.company.autopakiet.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "repair_history")
public class RepairHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne (cascade = CascadeType.DETACH )
    @JoinColumn (name = "service_repair_id")
    private ServiceRepair serviceRepair;

    public RepairHistory() {
    }

    public RepairHistory(LocalDate date, ServiceRepair serviceRepair) {
        this.date = date;
        this.serviceRepair = serviceRepair;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ServiceRepair getServiceRepair() {
        return serviceRepair;
    }

    public void setServiceRepair(ServiceRepair serviceRepair) {
        this.serviceRepair = serviceRepair;
    }

    @Override
    public String toString() {
        return "RepairHistory{" +
                "id=" + id +
                ", date=" + date +
                ", serviceRepair=" + serviceRepair +
                '}';
    }
}
