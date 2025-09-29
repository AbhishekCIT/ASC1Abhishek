package com.example.booking.exception;

import org.junit.jupiter.api.*;

/**
 * Unit tests for InvalidPassengerDetailsException.
 */
class InvalidPassengerDetailsExceptionTest {
    /**
     * Test exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        String msg = "Invalid passenger details";
        InvalidPassengerDetailsException ex = new InvalidPassengerDetailsException(msg);
        Assertions.assertEquals(msg, ex.getMessage());
    }

    /**
     * Test exception with null message (edge case).
     */
    @Test
    void testExceptionNullMessage() {
        InvalidPassengerDetailsException ex = new InvalidPassengerDetailsException(null);
        Assertions.assertNull(ex.getMessage());
    }
}
