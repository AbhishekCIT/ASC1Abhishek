package com.example.calculator.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for CalculationRequest DTO.
 * Covers getters, setters, toString, and edge cases.
 */
class CalculationRequestTest {
    private CalculationRequest request;

    @BeforeEach
    void setUp() {
        request = new CalculationRequest();
    }

    /**
     * Test getters and setters for all fields.
     */
    @Test
    @DisplayName("Getters and Setters Work Correctly")
    void testGettersAndSetters() {
        request.setNum1("12.5");
        request.setNum2("7.3");
        request.setOperation("add");
        assertEquals("12.5", request.getNum1());
        assertEquals("7.3", request.getNum2());
        assertEquals("add", request.getOperation());
    }

    /**
     * Test toString returns expected format.
     */
    @Test
    @DisplayName("toString Returns Expected Format")
    void testToString() {
        request.setNum1("1");
        request.setNum2("2");
        request.setOperation("multiply");
        String str = request.toString();
        assertTrue(str.contains("num1='1'"));
        assertTrue(str.contains("num2='2'"));
        assertTrue(str.contains("operation='multiply'"));
    }

    /**
     * Test edge case: null and empty values.
     */
    @Test
    @DisplayName("Edge Case: Null and Empty Values")
    void testNullAndEmptyValues() {
        request.setNum1(null);
        request.setNum2("");
        request.setOperation(null);
        assertNull(request.getNum1());
        assertEquals("", request.getNum2());
        assertNull(request.getOperation());
    }
}
