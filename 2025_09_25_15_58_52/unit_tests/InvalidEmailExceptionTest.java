package com.example.scheduler.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InvalidEmailException.
 * Verifies constructor and message propagation.
 */
class InvalidEmailExceptionTest {
    @Test
    @DisplayName("Test exception message propagation")
    void testMessagePropagation() {
        String msg = "Invalid email address.";
        InvalidEmailException ex = new InvalidEmailException(msg);
        assertEquals(msg, ex.getMessage());
    }

    @Test
    @DisplayName("Test exception is instance of RuntimeException")
    void testInstanceOfRuntimeException() {
        InvalidEmailException ex = new InvalidEmailException("msg");
        assertTrue(ex instanceof RuntimeException);
    }
}
