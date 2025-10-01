package com.example.flightsearch.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ExternalAPIException.
 */
class ExternalAPIExceptionTest {

    /**
     * Test normal scenario: constructor with message.
     */
    @Test
    @DisplayName("Constructor with message sets message correctly")
    void testConstructorWithMessage() {
        String msg = "API error";
        ExternalAPIException ex = new ExternalAPIException(msg);
        assertEquals(msg, ex.getMessage());
        assertNull(ex.getCause());
    }

    /**
     * Test normal scenario: constructor with message and cause.
     */
    @Test
    @DisplayName("Constructor with message and cause sets both correctly")
    void testConstructorWithMessageAndCause() {
        String msg = "API error";
        Throwable cause = new RuntimeException("Root cause");
        ExternalAPIException ex = new ExternalAPIException(msg, cause);
        assertEquals(msg, ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    /**
     * Test edge case: null message and cause.
     */
    @Test
    @DisplayName("Constructor with null message and cause")
    void testConstructorWithNulls() {
        ExternalAPIException ex = new ExternalAPIException(null, null);
        assertNull(ex.getMessage());
        assertNull(ex.getCause());
    }
}
