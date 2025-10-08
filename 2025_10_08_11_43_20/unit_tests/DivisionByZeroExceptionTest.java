package com.example.calculator.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for DivisionByZeroException.
 * Purpose: Test constructor and message propagation.
 */
public class DivisionByZeroExceptionTest {
    /**
     * Test exception message propagation.
     */
    @Test
    void testMessagePropagation() {
        DivisionByZeroException ex = new DivisionByZeroException("Division by zero is not allowed.");
        assertEquals("Division by zero is not allowed.", ex.getMessage());
    }
}
