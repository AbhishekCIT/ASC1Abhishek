package com.example.airbooking.repository;

import com.example.airbooking.entity.Seat;
import com.example.airbooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Seat entity.
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByFlightAndAvailableTrue(Flight flight);
    List<Seat> findByFlight(Flight flight);
}
