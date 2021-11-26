package com.kenez92.automanager.car;

import com.kenez92.automanager.fuel.Fuel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "CAR")
public class Car {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "PRODUCTION_YEAR")
    private Integer productionYear;

    @Column(name = "ENGINE")
    private String engine;

    @Column(name = "FUEL")
    private String fuel;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "car")
    private List<Fuel> fuelList;

    public Car() {
    }

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    void setModel(String model) {
        this.model = model;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getEngine() {
        return engine;
    }

    void setEngine(String engine) {
        this.engine = engine;
    }

    public String getFuel() {
        return fuel;
    }

    void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public List<Fuel> getFuelList() {
        return fuelList;
    }

    void setFuelList(List<Fuel> fuelList) {
        this.fuelList = fuelList;
    }
}
