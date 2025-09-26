package com.example.flightbooking.repository;

import com.example.flightbooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for Flight entity.
 */
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f WHERE f.origin = :origin AND f.destination = :destination AND DATE(f.departureTime) = :departureDate AND f.flightClass = :flightClass")
    List<Flight> findAvailableFlights(@Param("origin") String origin,
                                      @Param("destination") String destination,
                                      @Param("departureDate") LocalDate departureDate,
                                      @Param("returnDate") LocalDate returnDate,
                                      @Param("flightClass") String flightClass,
                                      @Param("passengers") int passengers);
}
