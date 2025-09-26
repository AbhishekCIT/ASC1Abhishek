package com.example.flightsearch.repository;

import com.example.flightsearch.entity.FlightResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for FlightResult entity.
 */
@Repository
public interface FlightResultRepository extends JpaRepository<FlightResult, Long> {
    List<FlightResult> findBySearchCriteriaId(Long searchCriteriaId);
}
