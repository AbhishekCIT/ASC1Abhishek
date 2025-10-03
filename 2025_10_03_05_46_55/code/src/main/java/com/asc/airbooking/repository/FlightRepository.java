package com.asc.airbooking.repository;

import com.asc.airbooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Flight entity data access.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    /**
     * Finds flights by origin, destination, and departure date.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param departureTime Departure date/time
     * @return List of matching flights
     */
    List<Flight> findByOriginAndDestinationAndDepartureTimeBetween(
        String origin, String destination, LocalDateTime start, LocalDateTime end);
}
