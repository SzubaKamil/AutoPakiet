package com.company.autopakiet.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "insurance")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private InsuranceType type;

    @Column (name = "begin_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate beginDate;

    @Column (name = "end_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    @Column (name = "sign_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate signDate;

    public Insurance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InsuranceType getType() {
        return type;
    }

    public void setType(InsuranceType type) {
        this.type = type;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    public enum InsuranceType {
        OC,
        AC
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "id=" + id +
                ", type=" + type +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", signDate=" + signDate +
                '}';
    }
}
