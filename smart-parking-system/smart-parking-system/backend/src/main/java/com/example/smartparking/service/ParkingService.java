package com.example.smartparking.service;

import com.example.smartparking.dto.ReservationRequest;
import com.example.smartparking.model.ParkingSpot;
import com.example.smartparking.model.Reservation;
import com.example.smartparking.repository.ParkingSpotRepository;
import com.example.smartparking.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ReservationRepository reservationRepository;

    public ParkingService(ParkingSpotRepository parkingSpotRepository,
                          ReservationRepository reservationRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<ParkingSpot> getAllSpots() {
        return parkingSpotRepository.findAll();
    }

    public List<ParkingSpot> getAvailableSpots() {
        return parkingSpotRepository.findByOccupiedFalse();
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional
    public Reservation createReservation(ReservationRequest request) {
        ParkingSpot spot = parkingSpotRepository.findById(request.getSpotId())
                .orElseThrow(() -> new RuntimeException("Spot not found"));

        if (spot.isOccupied()) {
            throw new RuntimeException("Spot already occupied");
        }

        LocalDateTime start = LocalDateTime.parse(request.getStartTime());
        LocalDateTime end = LocalDateTime.parse(request.getEndTime());

        Reservation reservation = new Reservation(
                request.getVehicleNumber(),
                request.getVehicleType(),
                start,
                end,
                spot
        );

        spot.setOccupied(true);
        spot.setVehicleNumber(request.getVehicleNumber());
        spot.setVehicleType(request.getVehicleType());
        parkingSpotRepository.save(spot);

        return reservationRepository.save(reservation);
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        ParkingSpot spot = reservation.getParkingSpot();
        spot.setOccupied(false);
        spot.setVehicleNumber(null);
        spot.setVehicleType(null);
        parkingSpotRepository.save(spot);

        reservationRepository.delete(reservation);
    }
}
