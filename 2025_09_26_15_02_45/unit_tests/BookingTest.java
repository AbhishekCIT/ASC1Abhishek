package com.example.airbooking.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    @Test
    @DisplayName("Test all getters and setters for Booking")
    void testGettersAndSetters() {
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setBookingRef("ABC123");
        booking.setUserId(2L);
        booking.setFlightId(3L);
        booking.setStatus("CONFIRMED");
        LocalDateTime now = LocalDateTime.now();
        booking.setBookingTime(now);
        booking.setEmail("test@example.com");
        List<String> seats = Arrays.asList("12A", "12B");
        booking.setSeats(seats);
        Object paymentInfo = new Object();
        booking.setPaymentInfo(paymentInfo);

        assertEquals(1L, booking.getId());
        assertEquals("ABC123", booking.getBookingRef());
        assertEquals(2L, booking.getUserId());
        assertEquals(3L, booking.getFlightId());
        assertEquals("CONFIRMED", booking.getStatus());
        assertEquals(now, booking.getBookingTime());
        assertEquals("test@example.com", booking.getEmail());
        assertEquals(seats, booking.getSeats());
        assertEquals(paymentInfo, booking.getPaymentInfo());
    }

    @Test
    @DisplayName("Test Booking with null and empty fields")
    void testNullAndEmptyFields() {
        Booking booking = new Booking();
        booking.setSeats(Collections.emptyList());
        booking.setPaymentInfo(null);
        assertNull(booking.getId());
        assertNull(booking.getBookingRef());
        assertNull(booking.getUserId());
        assertNull(booking.getFlightId());
        assertNull(booking.getStatus());
        assertNull(booking.getBookingTime());
        assertNull(booking.getEmail());
        assertEquals(Collections.emptyList(), booking.getSeats());
        assertNull(booking.getPaymentInfo());
    }
}
