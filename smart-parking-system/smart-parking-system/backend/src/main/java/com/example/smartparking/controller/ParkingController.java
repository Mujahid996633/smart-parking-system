package com.example.smartparking.controller;

import com.example.smartparking.dto.ReservationRequest;
import com.example.smartparking.model.ParkingSpot;
import com.example.smartparking.model.Reservation;
import com.example.smartparking.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/spots")
    public List<ParkingSpot> getAllSpots() {
        return parkingService.getAllSpots();
    }

    @GetMapping("/spots/available")
    public List<ParkingSpot> getAvailableSpots() {
        return parkingService.getAvailableSpots();
    }

    @GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return parkingService.getAllReservations();
    }

    @PostMapping("/reservations")
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest request) {
        try {
            Reservation reservation = parkingService.createReservation(request);
            return ResponseEntity.ok(reservation);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long id) {
        try {
            parkingService.cancelReservation(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
