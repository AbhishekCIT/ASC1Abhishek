package com.example.airtransport.repository;

import com.example.airtransport.model.Booking;
import com.example.airtransport.model.BookingRequest;
import com.example.airtransport.model.PaymentResult;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository for persisting booking data (stub for Azure SQL integration).
 */
@Repository
public class BookingRepository {
    // In-memory storage for demonstration
    private final Map<String, Booking> bookingStore = new HashMap<>();

    /**
     * Save a new booking.
     * @param bookingRequest Booking request data.
     * @param paymentResult Payment result.
     * @return Booking object.
     */
    public Booking saveBooking(BookingRequest bookingRequest, PaymentResult paymentResult) {
        Booking booking = new Booking();
        booking.setBookingId("B" + System.currentTimeMillis());
        booking.setUserId(bookingRequest.getUserId());
        booking.setFlightId(bookingRequest.getFlightId());
        booking.setStatus("CONFIRMED");
        booking.setTotalPrice(paymentResult.getAmount());
        booking.setBookingDate(java.time.LocalDateTime.now().toString());
        bookingStore.put(booking.getBookingId(), booking);
        return booking;
    }

    /**
     * Find a booking by booking ID.
     * @param bookingId Booking ID.
     * @return Booking object or null.
     */
    public Booking findByBookingId(String bookingId) {
        return bookingStore.get(bookingId);
    }
}
