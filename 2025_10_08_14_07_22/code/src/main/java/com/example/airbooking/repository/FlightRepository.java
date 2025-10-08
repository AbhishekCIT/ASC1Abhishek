package com.example.airbooking.repository;

import com.example.airbooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
