package com.airtransport.repository;

import com.airtransport.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByDestinationAndDepartureBetweenAndAirline(
        String destination,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String airline
    );
    List<Flight> findByDestinationAndDepartureBetween(
        String destination,
        LocalDateTime startDate,
        LocalDateTime endDate
    );
    List<Flight> findByDestination(String destination);
}
