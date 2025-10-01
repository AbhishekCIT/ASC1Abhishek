package com.airline.booking.repository;

import com.airline.booking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByOriginAndDestinationAndDepartureBetweenAndFlightClass(
            String origin, String destination, LocalDateTime start, LocalDateTime end, String flightClass);
}
