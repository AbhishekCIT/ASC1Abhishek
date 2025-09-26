package com.airtransport.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NotFoundException.
 */
public class NotFoundExceptionTest {
    /**
     * Test exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        String message = "Entity not found";
        NotFoundException ex = new NotFoundException(message);
        assertEquals(message, ex.getMessage());
    }

    /**
     * Test exception is instance of RuntimeException.
     */
    @Test
    void testExceptionInheritance() {
        NotFoundException ex = new NotFoundException("msg");
        assertTrue(ex instanceof RuntimeException);
    }
}
