package com.airtransport.repository;

import com.airtransport.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, String> {
    List<FlightEntity> findByOriginAndDestinationAndDate(String origin, String destination, String date);
    Optional<FlightEntity> findByFlightId(String flightId);
    // Custom method to decrement seats available
    default void decrementSeatsAvailable(String flightId) {
        findByFlightId(flightId).ifPresent(flight -> {
            flight.setSeatsAvailable(flight.getSeatsAvailable() - 1);
            save(flight);
        });
    }
}
