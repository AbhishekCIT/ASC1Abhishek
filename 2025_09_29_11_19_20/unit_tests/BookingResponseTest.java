package com.example.airbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookingResponse model.
 */
class BookingResponseTest {
    private BookingResponse response;

    @BeforeEach
    void setUp() {
        response = new BookingResponse();
    }

    @Test
    @DisplayName("Test default constructor and setters/getters")
    void testDefaultConstructorAndSetters() {
        response.setBookingId(55L);
        response.setStatus("CONFIRMED");
        response.setEmailSent(true);
        response.setError("none");
        assertEquals(55L, response.getBookingId());
        assertEquals("CONFIRMED", response.getStatus());
        assertTrue(response.isEmailSent());
        assertEquals("none", response.getError());
    }

    @Test
    @DisplayName("Test parameterized constructor")
    void testParameterizedConstructor() {
        BookingResponse r = new BookingResponse(77L, "FAILED", false, "error msg");
        assertEquals(77L, r.getBookingId());
        assertEquals("FAILED", r.getStatus());
        assertFalse(r.isEmailSent());
        assertEquals("error msg", r.getError());
    }

    @Test
    @DisplayName("Test null and boundary values")
    void testNullAndBoundaryValues() {
        response.setBookingId(null);
        response.setStatus(null);
        response.setError(null);
        assertNull(response.getBookingId());
        assertNull(response.getStatus());
        assertNull(response.getError());
        response.setEmailSent(false);
        assertFalse(response.isEmailSent());
    }
}
