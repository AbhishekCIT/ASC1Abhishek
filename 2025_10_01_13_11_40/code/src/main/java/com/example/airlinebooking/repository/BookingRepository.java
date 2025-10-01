package com.example.airlinebooking.repository;

import com.example.airlinebooking.entity.Booking;
import com.example.airlinebooking.entity.User;
import com.example.airlinebooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findByUser(User user);
    List<Booking> findByFlight(Flight flight);
}
