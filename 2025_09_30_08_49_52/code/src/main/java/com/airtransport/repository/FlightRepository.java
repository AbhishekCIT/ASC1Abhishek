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
    List<Flight> findByAirlineAndDepartureBetweenAndFlightClass(String airline, LocalDateTime start, LocalDateTime end, String flightClass);
    List<Flight> findByDepartureBetweenAndFlightClass(LocalDateTime start, LocalDateTime end, String flightClass);
    List<Flight> findByDepartureBetween(LocalDateTime start, LocalDateTime end);
    // Custom query for search
    List<Flight> findByDepartureAfterAndFlightClassAndAirlineContainingAndDepartureBetween(
        LocalDateTime now, String flightClass, String airline, LocalDateTime start, LocalDateTime end);
}
