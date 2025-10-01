package com.airtransport.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Booking entity.
 */
class BookingTest {

    /**
     * Test getters and setters for Booking.
     */
    @Test
    void testBookingGettersSetters() {
        Booking booking = new Booking();
        booking.setBookingId("B123");
        booking.setUserId("U456");
        booking.setFlightId("F789");
        booking.setStatus("CONFIRMED");
        booking.setTicketNumber("T0001");
        LocalDateTime now = LocalDateTime.now();
        booking.setBookingDate(now);
        Map<String, Object> passengerInfo = new HashMap<>();
        passengerInfo.put("name", "John Doe");
        booking.setPassengerInfo(passengerInfo);
        booking.setPrice(410.0);

        assertEquals("B123", booking.getBookingId());
        assertEquals("U456", booking.getUserId());
        assertEquals("F789", booking.getFlightId());
        assertEquals("CONFIRMED", booking.getStatus());
        assertEquals("T0001", booking.getTicketNumber());
        assertEquals(now, booking.getBookingDate());
        assertEquals(passengerInfo, booking.getPassengerInfo());
        assertEquals(410.0, booking.getPrice());
    }
}
