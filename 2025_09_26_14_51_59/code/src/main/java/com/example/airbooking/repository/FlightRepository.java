package com.example.airbooking.repository;

import com.example.airbooking.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for flight data access.
 */
@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
    // Custom query methods can be added here
}
