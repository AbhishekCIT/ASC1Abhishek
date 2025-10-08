package com.example.calculator.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for CalculationRequest.
 * Purpose: Test constructors, getters, and setters for normal and edge cases.
 */
public class CalculationRequestTest {
    /**
     * Test default constructor and setters.
     */
    @Test
    void testDefaultConstructorAndSetters() {
        CalculationRequest request = new CalculationRequest();
        request.setNumber1(10.0);
        request.setNumber2(20.0);
        request.setOperation("add");
        assertEquals(10.0, request.getNumber1());
        assertEquals(20.0, request.getNumber2());
        assertEquals("add", request.getOperation());
    }

    /**
     * Test parameterized constructor.
     */
    @Test
    void testParameterizedConstructor() {
        CalculationRequest request = new CalculationRequest(5.0, 7.0, "multiply");
        assertEquals(5.0, request.getNumber1());
        assertEquals(7.0, request.getNumber2());
        assertEquals("multiply", request.getOperation());
    }

    /**
     * Test edge case: null values.
     */
    @Test
    void testNullValues() {
        CalculationRequest request = new CalculationRequest(null, null, null);
        assertNull(request.getNumber1());
        assertNull(request.getNumber2());
        assertNull(request.getOperation());
    }
}
