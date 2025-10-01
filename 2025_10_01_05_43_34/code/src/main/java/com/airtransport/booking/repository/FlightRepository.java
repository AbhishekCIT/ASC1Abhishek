package com.airtransport.booking.repository;

import com.airtransport.booking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByOriginAndDestinationAndDepartureBetween(
        String origin, String destination, LocalDateTime start, LocalDateTime end);
}
