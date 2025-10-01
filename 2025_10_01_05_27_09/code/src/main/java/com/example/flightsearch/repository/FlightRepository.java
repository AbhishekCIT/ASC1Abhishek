package com.example.flightsearch.repository;

import com.example.flightsearch.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for accessing flight data.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    /**
     * Finds flights by origin, destination, and date.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param date Departure date
     * @return List of matching flights
     */
    List<Flight> findByOriginAndDestinationAndDate(String origin, String destination, LocalDate date);
}
