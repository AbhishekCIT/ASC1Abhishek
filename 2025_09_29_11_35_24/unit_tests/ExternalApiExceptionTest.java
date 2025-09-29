package com.airtransport.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ExternalApiException.
 */
public class ExternalApiExceptionTest {
    @Test
    @DisplayName("Test constructor with message")
    void testConstructorWithMessage() {
        ExternalApiException ex = new ExternalApiException("API error");
        assertEquals("API error", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("Test constructor with message and cause")
    void testConstructorWithMessageAndCause() {
        Throwable cause = new RuntimeException("Timeout");
        ExternalApiException ex = new ExternalApiException("API error", cause);
        assertEquals("API error", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    @DisplayName("Test constructor with null message and null cause (edge case)")
    void testConstructorWithNulls() {
        ExternalApiException ex = new ExternalApiException(null, null);
        assertNull(ex.getMessage());
        assertNull(ex.getCause());
    }
}
