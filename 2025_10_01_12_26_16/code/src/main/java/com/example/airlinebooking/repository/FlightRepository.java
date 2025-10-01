package com.example.airlinebooking.repository;

import com.example.airlinebooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByDepartureAndDestinationAndFlightClass(String departure, String destination, String flightClass);
    List<Flight> findByDestinationAndFlightClass(String destination, String flightClass);
    List<Flight> findByDepartureAndDestination(String departure, String destination);
}
