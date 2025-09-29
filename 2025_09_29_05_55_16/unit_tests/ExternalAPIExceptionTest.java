package com.example.airtransport.exception;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ExternalAPIException.
 * Covers constructor and message propagation.
 */
class ExternalAPIExceptionTest {
    @Test
    void constructor_setsMessageCorrectly() {
        // Purpose: Ensure message is set and retrievable
        String msg = "External API failed";
        ExternalAPIException ex = new ExternalAPIException(msg);
        assertEquals(msg, ex.getMessage());
    }

    @Test
    void constructor_nullMessage_allowed() {
        // Purpose: Allow null message
        ExternalAPIException ex = new ExternalAPIException(null);
        assertNull(ex.getMessage());
    }
}
