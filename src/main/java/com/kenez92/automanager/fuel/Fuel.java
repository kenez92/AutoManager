package com.kenez92.automanager.fuel;

import com.kenez92.automanager.car.Car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "FUEL")
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Liters", nullable = false)
    private Float liters;

    @Column(name = "COMPUTER AVERAGE_FUEL_CONSUMPTION", columnDefinition = "DECIMAL(3,2)")
    private Float computerAvgFuelConsumption;

    @Column(name = "REAL_AVERAGE_FUEL_CONSUMPTION", columnDefinition = "DECIMAL(3,2)")
    private Float realAvgFuelConsumption;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "COST")
    private Double cost;

    @ManyToOne
    private Car car;

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    Float getLiters() {
        return liters;
    }

    void setLiters(Float liters) {
        this.liters = liters;
    }

    Float getComputerAvgFuelConsumption() {
        return computerAvgFuelConsumption;
    }

    void setComputerAvgFuelConsumption(Float computerAvgFuelConsumption) {
        this.computerAvgFuelConsumption = computerAvgFuelConsumption;
    }

    Float getRealAvgFuelConsumption() {
        return realAvgFuelConsumption;
    }

    void setRealAvgFuelConsumption(Float realAvgFuelConsumption) {
        this.realAvgFuelConsumption = realAvgFuelConsumption;
    }

    Date getDate() {
        return date;
    }

    void setDate(Date date) {
        this.date = date;
    }

    Double getCost() {
        return cost;
    }

    void setCost(Double cost) {
        this.cost = cost;
    }

    Car getCar() {
        return car;
    }

    void setCar(Car car) {
        this.car = car;
    }
}