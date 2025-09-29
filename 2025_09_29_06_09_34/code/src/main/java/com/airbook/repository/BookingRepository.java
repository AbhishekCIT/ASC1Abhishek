package com.airbook.repository;

import com.airbook.model.Booking;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * Find all bookings for a user (mock implementation)
     */
    public List<Booking> findBookingsByUser(String userId) {
        return bookingStore.values().stream()
                .filter(b -> userId.equals(b.getUserId()))
                .collect(Collectors.toList());
    }

    /**
     * Find booking history for a user (mock implementation)
     */
    public List<Booking> findBookingHistoryByUser(String userId) {
        return bookingStore.values().stream()
                .filter(b -> userId.equals(b.getUserId()) && "CANCELLED".equals(b.getStatus()))
                .collect(Collectors.toList());
    }
}
