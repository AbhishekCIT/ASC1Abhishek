package com.example.airline.repository;

import com.example.airline.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findByFlight_FlightIdAndIsAvailableTrue(String flightId);
}