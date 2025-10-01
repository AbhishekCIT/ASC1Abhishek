package com.example.airbooking.repository;

import com.example.airbooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByOriginAndDestinationAndDepartureDate(String origin, String destination, LocalDate departureDate);
    List<Flight> findByOriginAndDestinationAndDepartureDateBetween(String origin, String destination, LocalDate start, LocalDate end);
}
