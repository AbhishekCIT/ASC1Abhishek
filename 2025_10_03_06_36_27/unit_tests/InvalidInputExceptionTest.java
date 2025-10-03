package com.example.airbooking.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InvalidInputException
 */
public class InvalidInputExceptionTest {
    /**
     * Test exception message is set correctly
     */
    @Test
    void testMessage() {
        InvalidInputException ex = new InvalidInputException("Invalid input");
        assertEquals("Invalid input", ex.getMessage());
    }

    /**
     * Test exception with null message
     */
    @Test
    void testNullMessage() {
        InvalidInputException ex = new InvalidInputException(null);
        assertNull(ex.getMessage());
    }
}
