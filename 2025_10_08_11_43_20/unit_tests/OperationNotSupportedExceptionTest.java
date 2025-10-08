package com.example.calculator.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for OperationNotSupportedException.
 * Purpose: Test constructor and message propagation.
 */
public class OperationNotSupportedExceptionTest {
    /**
     * Test exception message propagation.
     */
    @Test
    void testMessagePropagation() {
        OperationNotSupportedException ex = new OperationNotSupportedException("Invalid operation");
        assertEquals("Invalid operation", ex.getMessage());
    }
}
