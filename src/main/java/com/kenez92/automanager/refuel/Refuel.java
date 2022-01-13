package com.kenez92.automanager.refuel;

import com.kenez92.automanager.car.Car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "REFUEL")
public class Refuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "KM_MILEAGE")
    private Long mileage;

    @Column(name = "KM_TRAVELED")
    private Double kmTraveled;

    @Column(name = "LITERS", nullable = false, columnDefinition = "DECIMAL(7,2)")
    private Float liters;

    @Column(name = "COMPUTER_AVERAGE_FUEL_CONSUMPTION", columnDefinition = "DECIMAL(7,2)")
    private Float computerAvgFuelConsumption;

    @Column(name = "REAL_AVERAGE_FUEL_CONSUMPTION", columnDefinition = "DECIMAL(7,2)")
    private Float realAvgFuelConsumption;

    @Column(name = "DATE", nullable = false)
    private Date date;

    @Column(name = "COST")
    private Double cost;

    @Column(name = "COST_PER_LITER", columnDefinition = "DECIMAL(5,2)")
    private Double costPerLiter;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @ManyToOne
    private Car car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public Double getKmTraveled() {
        return kmTraveled;
    }

    public void setKmTraveled(Double kmTraveled) {
        this.kmTraveled = kmTraveled;
    }

    public Float getLiters() {
        return liters;
    }

    public void setLiters(Float liters) {
        this.liters = liters;
    }

    public Float getComputerAvgFuelConsumption() {
        return computerAvgFuelConsumption;
    }

    public void setComputerAvgFuelConsumption(Float computerAvgFuelConsumption) {
        this.computerAvgFuelConsumption = computerAvgFuelConsumption;
    }

    public Float getRealAvgFuelConsumption() {
        return realAvgFuelConsumption;
    }

    public void setRealAvgFuelConsumption(Float realAvgFuelConsumption) {
        this.realAvgFuelConsumption = realAvgFuelConsumption;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getCostPerLiter() {
        return costPerLiter;
    }

    public void setCostPerLiter(Double costPerLiter) {
        this.costPerLiter = costPerLiter;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Car getCar() {
        return car;
    }

    void setCar(Car car) {
        this.car = car;
    }

    public void calculateRealAvgConsumptionByKmTraveled() {
        if (liters != null && kmTraveled != null) {
            float result = (float) (liters / kmTraveled) * 100;
            if (result > 0) {
                realAvgFuelConsumption = result;
            }
        }
    }

    public void calculateRealAvgConsumptionByMileage(Long lastMileage) {
        if (lastMileage != null && mileage != null && liters != null) {
            float result = (mileage - lastMileage) / liters * 100;
            if (result > 0) {
                realAvgFuelConsumption = result;
            }
        }
    }

    public void calculateCostPerLiter() {
        if (liters != null && cost != null) {
            double result = cost / liters;
            if (result > 0) {
                costPerLiter = result;
            }
        }
    }

    public void updateRefuel(RefuelDto refuelDto) {
        if (refuelDto != null) {
            if (!Objects.equals(mileage, refuelDto.getMileage())) {
                mileage = refuelDto.getMileage();
            }
            if (!Objects.equals(kmTraveled, refuelDto.getKmTraveled())) {
                kmTraveled = refuelDto.getKmTraveled();
            }
            if (!Objects.equals(liters, refuelDto.getLiters())) {
                liters = refuelDto.getLiters();
            }
            if (!Objects.equals(computerAvgFuelConsumption, refuelDto.getComputerAvgFuelConsumption())) {
                computerAvgFuelConsumption = refuelDto.getComputerAvgFuelConsumption();
            }
            if (!Objects.equals(realAvgFuelConsumption, refuelDto.getRealAvgFuelConsumption())) {
                realAvgFuelConsumption = refuelDto.getRealAvgFuelConsumption();
            }
            try {
                Date tempDate = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(refuelDto.getDate()));
                if (date == null || date.equals(tempDate)) {
                    date = tempDate;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (!Objects.equals(cost, refuelDto.getCost())) {
                cost = refuelDto.getCost();
            }
            if (!Objects.equals(costPerLiter, refuelDto.getRealAvgFuelConsumption())) {
                costPerLiter = refuelDto.getCostPerLiter();
            }
        }
    }
}
