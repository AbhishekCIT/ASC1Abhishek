package com.example.airtransport.repository;

import com.example.airtransport.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByDestinationAndDepartureTimeBetweenAndFlightClass(
        String destination, LocalDateTime start, LocalDateTime end, String flightClass);
}
