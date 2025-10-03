package com.airtransport.repository;

import com.airtransport.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findByFlight_FlightIdAndIsAvailable(Integer flightId, Boolean isAvailable);
}
