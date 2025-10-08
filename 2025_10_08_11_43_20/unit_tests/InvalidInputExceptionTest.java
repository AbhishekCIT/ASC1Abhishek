package com.example.calculator.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for InvalidInputException.
 * Purpose: Test constructor and message propagation.
 */
public class InvalidInputExceptionTest {
    /**
     * Test exception message propagation.
     */
    @Test
    void testMessagePropagation() {
        InvalidInputException ex = new InvalidInputException("Inputs cannot be empty");
        assertEquals("Inputs cannot be empty", ex.getMessage());
    }
}
