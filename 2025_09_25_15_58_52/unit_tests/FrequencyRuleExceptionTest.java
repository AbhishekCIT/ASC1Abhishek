package com.example.scheduler.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FrequencyRuleException.
 * Verifies constructor and message propagation.
 */
class FrequencyRuleExceptionTest {
    @Test
    @DisplayName("Test exception message propagation")
    void testMessagePropagation() {
        String msg = "Frequency not allowed.";
        FrequencyRuleException ex = new FrequencyRuleException(msg);
        assertEquals(msg, ex.getMessage());
    }

    @Test
    @DisplayName("Test exception is instance of RuntimeException")
    void testInstanceOfRuntimeException() {
        FrequencyRuleException ex = new FrequencyRuleException("msg");
        assertTrue(ex instanceof RuntimeException);
    }
}
