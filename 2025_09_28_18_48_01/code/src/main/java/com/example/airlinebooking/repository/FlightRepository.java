package com.example.airlinebooking.repository;

import com.example.airlinebooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    // Find flights by origin, destination, and date
    List<Flight> findByOriginAndDestinationAndDepartureTimeBetween(
            String origin, String destination, LocalDateTime start, LocalDateTime end);
}
