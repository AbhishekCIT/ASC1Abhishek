package com.airline.booking.repository;

import com.airline.booking.model.Seat;
import com.airline.booking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Seat entity.
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findByFlightAndSeatClass(Flight flight, String seatClass);
    Optional<Seat> findByFlightAndSeatNumber(Flight flight, String seatNumber);
    List<Seat> findByFlight(Flight flight);
}
