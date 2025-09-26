package com.example.flightsearch.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FilterMismatchException.
 */
public class FilterMismatchExceptionTest {
    @Test
    @DisplayName("Should create exception with message")
    // Tests constructor with message
    void testConstructorWithMessage() {
        FilterMismatchException ex = new FilterMismatchException("No flights match");
        assertEquals("No flights match", ex.getMessage());
    }
}
