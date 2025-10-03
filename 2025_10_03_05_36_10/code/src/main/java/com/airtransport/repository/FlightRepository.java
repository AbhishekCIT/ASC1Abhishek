package com.airtransport.repository;

import com.airtransport.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    // Find flights by destination, date, and number of passengers
    List<Flight> findByDestinationAndDepartureBetween(String destination, LocalDateTime start, LocalDateTime end);
}
