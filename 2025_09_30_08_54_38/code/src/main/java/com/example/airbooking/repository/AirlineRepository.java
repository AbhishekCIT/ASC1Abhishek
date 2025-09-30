package com.example.airbooking.repository;

import com.example.airbooking.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Airline entity.
 */
@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
}
