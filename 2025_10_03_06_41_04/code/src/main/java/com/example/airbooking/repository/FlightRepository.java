package com.example.airbooking.repository;

import com.example.airbooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f WHERE f.origin = :origin AND f.destination = :destination AND DATE(f.departure) = :date AND f.seatsAvailable >= :passengers")
    List<Flight> findAvailableFlights(@Param("origin") String origin, @Param("destination") String destination, @Param("date") String date, @Param("passengers") int passengers);
}
