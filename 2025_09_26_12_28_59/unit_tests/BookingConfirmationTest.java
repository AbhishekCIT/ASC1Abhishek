package com.example.flightbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingConfirmation model.
 */
class BookingConfirmationTest {
    private BookingConfirmation confirmation;

    @BeforeEach
    void setUp() {
        confirmation = new BookingConfirmation();
    }

    @Test
    @DisplayName("Should set and get bookingId correctly")
    void testBookingId() {
        confirmation.setBookingId("B123");
        assertEquals("B123", confirmation.getBookingId(), "BookingId should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get status correctly")
    void testStatus() {
        confirmation.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", confirmation.getStatus(), "Status should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get pnr correctly")
    void testPnr() {
        confirmation.setPnr("PNR123");
        assertEquals("PNR123", confirmation.getPnr(), "PNR should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should handle null values for all fields")
    void testNullValues() {
        confirmation.setBookingId(null);
        confirmation.setStatus(null);
        confirmation.setPnr(null);
        assertNull(confirmation.getBookingId());
        assertNull(confirmation.getStatus());
        assertNull(confirmation.getPnr());
    }
}
