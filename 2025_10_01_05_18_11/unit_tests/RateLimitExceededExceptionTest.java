package com.example.flightsearch.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RateLimitExceededException.
 */
class RateLimitExceededExceptionTest {

    /**
     * Test normal scenario: constructor with message.
     */
    @Test
    @DisplayName("Constructor with message sets message correctly")
    void testConstructorWithMessage() {
        String msg = "Rate limit exceeded";
        RateLimitExceededException ex = new RateLimitExceededException(msg);
        assertEquals(msg, ex.getMessage());
    }

    /**
     * Test edge case: null message.
     */
    @Test
    @DisplayName("Constructor with null message")
    void testConstructorWithNullMessage() {
        RateLimitExceededException ex = new RateLimitExceededException(null);
        assertNull(ex.getMessage());
    }
}
