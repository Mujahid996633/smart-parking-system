package com.example.smartparking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spotCode;
    private String level;
    private boolean occupied;

    private String vehicleNumber;
    private String vehicleType;

    public ParkingSpot() {
    }

    public ParkingSpot(String spotCode, String level) {
        this.spotCode = spotCode;
        this.level = level;
        this.occupied = false;
    }

    public Long getId() {
        return id;
    }

    public String getSpotCode() {
        return spotCode;
    }

    public void setSpotCode(String spotCode) {
        this.spotCode = spotCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
