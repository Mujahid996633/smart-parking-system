package com.example.smartparking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleNumber;
    private String vehicleType;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    private ParkingSpot parkingSpot;

    public Reservation() {
    }

    public Reservation(String vehicleNumber, String vehicleType,
                       LocalDateTime startTime, LocalDateTime endTime,
                       ParkingSpot parkingSpot) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.parkingSpot = parkingSpot;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
