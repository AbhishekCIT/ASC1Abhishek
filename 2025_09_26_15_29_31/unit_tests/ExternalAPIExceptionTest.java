package com.example.flightsearch.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ExternalAPIException.
 */
public class ExternalAPIExceptionTest {
    @Test
    @DisplayName("Should create exception with message")
    // Tests constructor with message
    void testConstructorWithMessage() {
        ExternalAPIException ex = new ExternalAPIException("API error");
        assertEquals("API error", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("Should create exception with message and cause")
    // Tests constructor with message and cause
    void testConstructorWithMessageAndCause() {
        Throwable cause = new RuntimeException("Root cause");
        ExternalAPIException ex = new ExternalAPIException("API error", cause);
        assertEquals("API error", ex.getMessage());
        assertSame(cause, ex.getCause());
    }
}
