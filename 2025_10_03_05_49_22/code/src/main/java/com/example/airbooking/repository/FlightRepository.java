package com.example.airbooking.repository;

import com.example.airbooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByFromAirportAndToAirportAndDepartureBetween(String from, String to, LocalDateTime start, LocalDateTime end);
}
