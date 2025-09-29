package com.example.airbooking.repository;

import com.example.airbooking.model.Seat;
import com.example.airbooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Seat entity.
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByFlightAndIsAvailable(Flight flight, Boolean isAvailable);
    Optional<Seat> findByFlightAndSeatNumber(Flight flight, String seatNumber);
}
