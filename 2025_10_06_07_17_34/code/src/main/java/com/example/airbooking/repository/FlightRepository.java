package com.example.airbooking.repository;

import com.example.airbooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    // Find flights by origin, destination, and date
    List<Flight> findByAirlineAndDepartureBetween(String airline, LocalDateTime start, LocalDateTime end);
    List<Flight> findByDepartureBetween(LocalDateTime start, LocalDateTime end);
    List<Flight> findByOriginAndDestinationAndDepartureBetween(String origin, String destination, LocalDateTime start, LocalDateTime end);
}