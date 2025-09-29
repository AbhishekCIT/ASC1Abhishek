package com.example.airtransport.exception;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SeatUnavailableException.
 * Covers constructor and message propagation.
 */
class SeatUnavailableExceptionTest {
    @Test
    void constructor_setsMessageCorrectly() {
        // Purpose: Ensure message is set and retrievable
        String msg = "No seats available";
        SeatUnavailableException ex = new SeatUnavailableException(msg);
        assertEquals(msg, ex.getMessage());
    }

    @Test
    void constructor_nullMessage_allowed() {
        // Purpose: Allow null message
        SeatUnavailableException ex = new SeatUnavailableException(null);
        assertNull(ex.getMessage());
    }
}
