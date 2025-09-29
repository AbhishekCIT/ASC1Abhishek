package com.airbook.repository;

import com.airbook.model.Booking;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository for booking data access
 */
@Repository
public class BookingRepository {
    private final Map<String, Booking> bookingStore = new HashMap<>();

    /**
     * Save booking (mock implementation)
     */
    public void save(Booking booking) {
        bookingStore.put(booking.getBookingId(), booking);
    }

    /**
     * Find booking by ID (mock implementation)
     */
    public Booking findById(String bookingId) {
        return bookingStore.get(bookingId);
    }
}
