package com.airtransport.repository;

import com.airtransport.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    // Custom query methods can be added here if needed
}
