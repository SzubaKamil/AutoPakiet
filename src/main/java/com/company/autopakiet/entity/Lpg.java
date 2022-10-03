package com.company.autopakiet.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Lpg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "installation_year")
    private int installationYear;


    @Column(name = "cylinder_validity")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate cylinderValidity;

    public Lpg() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInstallationYear() {
        return installationYear;
    }

    public void setInstallationYear(int installationYear) {
        this.installationYear = installationYear;
    }

    public LocalDate getCylinderValidity() {
        return cylinderValidity;
    }

    public void setCylinderValidity(LocalDate cylinderValidity) {
        this.cylinderValidity = cylinderValidity;
    }

    @Override
    public String toString() {
        return "LPG{" +
                "id=" + id +
                ", installationYear=" + installationYear +
                ", cylinderValidity=" + cylinderValidity +
                '}';
    }
}
