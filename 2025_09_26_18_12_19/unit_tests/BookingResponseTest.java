package com.example.flightbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingResponse model.
 */
class BookingResponseTest {
    private BookingResponse response;

    @BeforeEach
    void setUp() {
        response = new BookingResponse();
    }

    /**
     * Test default constructor and setters/getters.
     */
    @Test
    void testDefaultConstructorAndSetters() {
        response.setBookingId("B789");
        response.setStatus("CONFIRMED");
        response.setConfirmationEmail("sent");
        assertEquals("B789", response.getBookingId());
        assertEquals("CONFIRMED", response.getStatus());
        assertEquals("sent", response.getConfirmationEmail());
    }

    /**
     * Test parameterized constructor.
     */
    @Test
    void testParameterizedConstructor() {
        BookingResponse resp = new BookingResponse("B123", "PENDING", "not_sent");
        assertEquals("B123", resp.getBookingId());
        assertEquals("PENDING", resp.getStatus());
        assertEquals("not_sent", resp.getConfirmationEmail());
    }

    /**
     * Test setting null values.
     */
    @Test
    void testNullValues() {
        response.setBookingId(null);
        response.setStatus(null);
        response.setConfirmationEmail(null);
        assertNull(response.getBookingId());
        assertNull(response.getStatus());
        assertNull(response.getConfirmationEmail());
    }
}
