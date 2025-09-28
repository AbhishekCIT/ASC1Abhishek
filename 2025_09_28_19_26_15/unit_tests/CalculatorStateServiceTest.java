package com.example.calculator.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for CalculatorStateService.
 * Verifies clearState() executes without error.
 */
class CalculatorStateServiceTest {
    /**
     * Test clearState executes without exception.
     */
    @Test
    @DisplayName("clearState Executes Without Exception")
    void testClearState() {
        CalculatorStateService service = new CalculatorStateService();
        assertDoesNotThrow(service::clearState);
    }
}
