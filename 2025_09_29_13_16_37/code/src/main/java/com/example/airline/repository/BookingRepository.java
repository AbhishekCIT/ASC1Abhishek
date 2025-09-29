package com.example.airline.repository;

import com.example.airline.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findByUser_UserId(String userId);
    List<Booking> findByFlight_FlightId(String flightId);
}