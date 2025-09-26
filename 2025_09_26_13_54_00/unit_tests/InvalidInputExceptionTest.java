package com.airtransport.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InvalidInputException.
 */
public class InvalidInputExceptionTest {
    /**
     * Test exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        String message = "Invalid input provided";
        InvalidInputException ex = new InvalidInputException(message);
        assertEquals(message, ex.getMessage());
    }

    /**
     * Test exception is instance of RuntimeException.
     */
    @Test
    void testExceptionInheritance() {
        InvalidInputException ex = new InvalidInputException("msg");
        assertTrue(ex instanceof RuntimeException);
    }
}
