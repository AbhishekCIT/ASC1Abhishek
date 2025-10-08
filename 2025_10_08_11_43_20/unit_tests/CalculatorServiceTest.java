package com.example.calculator.service;

import com.example.calculator.exception.DivisionByZeroException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for CalculatorService.
 * Purpose: Test all arithmetic operations, edge cases, boundaries, and error scenarios.
 */
public class CalculatorServiceTest {
    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    /**
     * Test addition with normal values.
     */
    @Test
    void testAdd_NormalValues() {
        assertEquals(5.0, calculatorService.add(2.0, 3.0));
    }

    /**
     * Test addition with negative values.
     */
    @Test
    void testAdd_NegativeValues() {
        assertEquals(-1.0, calculatorService.add(2.0, -3.0));
    }

    /**
     * Test addition with zero.
     */
    @Test
    void testAdd_Zero() {
        assertEquals(2.0, calculatorService.add(2.0, 0.0));
    }

    /**
     * Test subtraction with normal values.
     */
    @Test
    void testSubtract_NormalValues() {
        assertEquals(-1.0, calculatorService.subtract(2.0, 3.0));
    }

    /**
     * Test subtraction with negative values.
     */
    @Test
    void testSubtract_NegativeValues() {
        assertEquals(5.0, calculatorService.subtract(2.0, -3.0));
    }

    /**
     * Test subtraction with zero.
     */
    @Test
    void testSubtract_Zero() {
        assertEquals(2.0, calculatorService.subtract(2.0, 0.0));
    }

    /**
     * Test multiplication with normal values.
     */
    @Test
    void testMultiply_NormalValues() {
        assertEquals(6.0, calculatorService.multiply(2.0, 3.0));
    }

    /**
     * Test multiplication with zero.
     */
    @Test
    void testMultiply_Zero() {
        assertEquals(0.0, calculatorService.multiply(2.0, 0.0));
    }

    /**
     * Test multiplication with negative values.
     */
    @Test
    void testMultiply_NegativeValues() {
        assertEquals(-6.0, calculatorService.multiply(2.0, -3.0));
    }

    /**
     * Test division with normal values.
     */
    @Test
    void testDivide_NormalValues() {
        assertEquals(2.0, calculatorService.divide(6.0, 3.0));
    }

    /**
     * Test division with decimal values.
     */
    @Test
    void testDivide_DecimalValues() {
        assertEquals(2.5, calculatorService.divide(5.0, 2.0));
    }

    /**
     * Test division by zero throws exception.
     */
    @Test
    void testDivide_ByZero() {
        DivisionByZeroException exception = assertThrows(DivisionByZeroException.class, () -> calculatorService.divide(5.0, 0.0));
        assertEquals("Division by zero is not allowed.", exception.getMessage());
    }

    /**
     * Test division with negative values.
     */
    @Test
    void testDivide_NegativeValues() {
        assertEquals(-2.0, calculatorService.divide(6.0, -3.0));
    }
}
