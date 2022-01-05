package com.kenez92.automanager.fuel;

public class FuelDto {
    private Long carId;
    private Long mileage;
    private Double kmTraveled;
    private Float liters;
    private Float computerAvgFuelConsumption;
    private Float realAvgFuelConsumption;
    private String date;
    private Double cost;
    private Double costPerLiter;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}
