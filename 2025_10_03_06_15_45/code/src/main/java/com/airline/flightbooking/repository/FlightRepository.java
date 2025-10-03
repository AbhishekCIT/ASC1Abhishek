package com.airline.flightbooking.repository;

import com.airline.flightbooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByOriginAndDestinationAndDate(String origin, String destination, LocalDate date);
}
