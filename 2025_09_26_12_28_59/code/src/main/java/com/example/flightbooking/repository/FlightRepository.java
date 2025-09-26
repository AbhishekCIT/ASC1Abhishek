package com.example.flightbooking.repository;

import com.example.flightbooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CRUD operations on Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
}
