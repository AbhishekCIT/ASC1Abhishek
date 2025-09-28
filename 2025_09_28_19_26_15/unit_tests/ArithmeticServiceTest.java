package com.example.calculator.service;

import com.example.calculator.exception.DivisionByZeroException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for ArithmeticService.
 * Covers all arithmetic operations, normal, edge, boundary, and error cases.
 */
class ArithmeticServiceTest {
    private ArithmeticService service;

    @BeforeEach
    void setUp() {
        service = new ArithmeticService();
    }

    /**
     * Test addition of two positive numbers.
     */
    @Test
    @DisplayName("Addition of Two Positive Numbers")
    void testAddPositive() {
        assertEquals(5.0, service.add(2.0, 3.0));
    }

    /**
     * Test addition with negative numbers.
     */
    @Test
    @DisplayName("Addition with Negative Numbers")
    void testAddNegative() {
        assertEquals(-1.0, service.add(2.0, -3.0));
    }

    /**
     * Test subtraction resulting in negative value.
     */
    @Test
    @DisplayName("Subtraction Resulting in Negative Value")
    void testSubtractNegativeResult() {
        assertEquals(-1.0, service.subtract(2.0, 3.0));
    }

    /**
     * Test subtraction with zero.
     */
    @Test
    @DisplayName("Subtraction with Zero")
    void testSubtractZero() {
        assertEquals(2.0, service.subtract(2.0, 0.0));
    }

    /**
     * Test multiplication of two numbers.
     */
    @Test
    @DisplayName("Multiplication of Two Numbers")
    void testMultiply() {
        assertEquals(6.0, service.multiply(2.0, 3.0));
    }

    /**
     * Test multiplication by zero.
     */
    @Test
    @DisplayName("Multiplication by Zero")
    void testMultiplyByZero() {
        assertEquals(0.0, service.multiply(2.0, 0.0));
    }

    /**
     * Test division of two numbers.
     */
    @Test
    @DisplayName("Division of Two Numbers")
    void testDivide() {
        assertEquals(2.0, service.divide(6.0, 3.0));
    }

    /**
     * Test division by zero throws exception.
     */
    @Test
    @DisplayName("Division by Zero Throws Exception")
    void testDivideByZero() {
        DivisionByZeroException ex = assertThrows(DivisionByZeroException.class, () -> service.divide(5.0, 0.0));
        assertEquals("Division by zero is not allowed.", ex.getMessage());
    }

    /**
     * Test division with negative divisor.
     */
    @Test
    @DisplayName("Division with Negative Divisor")
    void testDivideNegativeDivisor() {
        assertEquals(-2.0, service.divide(6.0, -3.0));
    }

    /**
     * Test boundary case: very large numbers.
     */
    @Test
    @DisplayName("Boundary Case: Very Large Numbers")
    void testLargeNumbers() {
        double result = service.add(Double.MAX_VALUE, 1.0);
        assertTrue(Double.isInfinite(result) || result > 0);
    }

    /**
     * Test boundary case: very small numbers.
     */
    @Test
    @DisplayName("Boundary Case: Very Small Numbers")
    void testSmallNumbers() {
        double result = service.subtract(Double.MIN_VALUE, 1.0);
        assertEquals(-1.0, result, 1e-10);
    }
}
