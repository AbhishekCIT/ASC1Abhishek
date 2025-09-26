package com.airtransport.repository;

import com.airtransport.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for FlightEntity.
 */
@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, String> {
    // Custom query methods can be added here
}
