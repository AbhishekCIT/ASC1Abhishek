package com.example.flightbooking.repository;

import com.example.flightbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Booking entity.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByBookingReference(String bookingReference);
}
