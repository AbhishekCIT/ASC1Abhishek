package com.example.scheduler.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PastDateException.
 * Verifies constructor and message propagation.
 */
class PastDateExceptionTest {
    @Test
    @DisplayName("Test exception message propagation")
    void testMessagePropagation() {
        String msg = "Cannot schedule for past date/time.";
        PastDateException ex = new PastDateException(msg);
        assertEquals(msg, ex.getMessage());
    }

    @Test
    @DisplayName("Test exception is instance of RuntimeException")
    void testInstanceOfRuntimeException() {
        PastDateException ex = new PastDateException("msg");
        assertTrue(ex instanceof RuntimeException);
    }
}
