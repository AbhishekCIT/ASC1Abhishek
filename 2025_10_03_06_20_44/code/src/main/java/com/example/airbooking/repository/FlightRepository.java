package com.example.airbooking.repository;

import com.example.airbooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByFromAirportAndToAirportAndDepartureBetweenAndSeatsAvailableGreaterThan(
        String fromAirport, String toAirport, LocalDateTime start, LocalDateTime end, int minSeats);
}
