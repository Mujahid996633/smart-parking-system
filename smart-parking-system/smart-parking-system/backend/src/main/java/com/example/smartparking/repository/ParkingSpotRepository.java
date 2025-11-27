package com.example.smartparking.repository;

import com.example.smartparking.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByOccupiedFalse();
}
