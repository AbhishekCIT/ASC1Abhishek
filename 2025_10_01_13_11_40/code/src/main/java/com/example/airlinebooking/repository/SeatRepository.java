package com.example.airlinebooking.repository;

import com.example.airlinebooking.entity.Seat;
import com.example.airlinebooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Seat entity.
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findByFlightAndIsAvailable(Flight flight, Boolean isAvailable);
    Seat findByFlightAndSeatNumber(Flight flight, String seatNumber);
}
