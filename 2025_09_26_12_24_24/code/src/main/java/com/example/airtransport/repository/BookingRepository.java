package com.example.airtransport.repository;

import com.example.airtransport.model.Booking;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Repository for accessing booking data.
 * In a real application, this would extend JpaRepository or similar.
 */
@Repository
public class BookingRepository {
    private final Map<String, Booking> bookings = new HashMap<>();

    public void save(Booking booking) {
        bookings.put(booking.getBookingReference(), booking);
    }

    public Optional<Booking> findByBookingReference(String reference) {
        return Optional.ofNullable(bookings.get(reference));
    }
}
