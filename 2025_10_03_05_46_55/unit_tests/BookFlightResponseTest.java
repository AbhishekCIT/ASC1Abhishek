package com.asc.airbooking.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookFlightResponse.
 * Covers setters, getters, and edge cases.
 */
class BookFlightResponseTest {

    /**
     * Purpose: Test setting and getting confirmationId and status.
     */
    @Test
    void testSettersAndGetters() {
        BookFlightResponse response = new BookFlightResponse();
        response.setConfirmationId("CONF123");
        response.setStatus("confirmed");
        assertEquals("CONF123", response.getConfirmationId());
        assertEquals("confirmed", response.getStatus());
    }

    /**
     * Purpose: Test edge case with null values.
     */
    @Test
    void testNullValues() {
        BookFlightResponse response = new BookFlightResponse();
        response.setConfirmationId(null);
        response.setStatus(null);
        assertNull(response.getConfirmationId());
        assertNull(response.getStatus());
    }

    /**
     * Purpose: Test edge case with blank values.
     */
    @Test
    void testBlankValues() {
        BookFlightResponse response = new BookFlightResponse();
        response.setConfirmationId("");
        response.setStatus("");
        assertEquals("", response.getConfirmationId());
        assertEquals("", response.getStatus());
    }
}
