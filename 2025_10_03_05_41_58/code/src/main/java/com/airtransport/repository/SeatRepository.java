package com.airtransport.repository;

import com.airtransport.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Seat entity.
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findByFlightFlightId(String flightId);
}
