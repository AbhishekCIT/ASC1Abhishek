package com.airtransport.repository;

import com.airtransport.model.FlightOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository for FlightOption entity.
 */
@Repository
public interface FlightOptionRepository extends JpaRepository<FlightOption, Long> {
    List<FlightOption> findByOriginAndDestinationAndDate(String origin, String destination, LocalDate date);
}
