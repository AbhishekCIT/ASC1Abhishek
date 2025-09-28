package com.example.calculator.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for CalculationHistoryItem DTO.
 * Covers getters, setters, equals, hashCode, toString, and edge cases.
 */
class CalculationHistoryItemTest {
    private CalculationHistoryItem item;

    @BeforeEach
    void setUp() {
        item = new CalculationHistoryItem();
    }

    /**
     * Test getters and setters for all fields.
     */
    @Test
    @DisplayName("Getters and Setters Work Correctly")
    void testGettersAndSetters() {
        item.setInput1(2.5);
        item.setOperation("add");
        item.setInput2(3.5);
        item.setResult(6.0);
        assertEquals(2.5, item.getInput1());
        assertEquals("add", item.getOperation());
        assertEquals(3.5, item.getInput2());
        assertEquals(6.0, item.getResult());
    }

    /**
     * Test equals and hashCode for identical objects.
     */
    @Test
    @DisplayName("Equals and HashCode for Identical Objects")
    void testEqualsAndHashCodeIdentical() {
        CalculationHistoryItem item2 = new CalculationHistoryItem();
        item.setInput1(1.0);
        item.setOperation("subtract");
        item.setInput2(2.0);
        item.setResult(-1.0);
        item2.setInput1(1.0);
        item2.setOperation("subtract");
        item2.setInput2(2.0);
        item2.setResult(-1.0);
        assertEquals(item, item2);
        assertEquals(item.hashCode(), item2.hashCode());
    }

    /**
     * Test equals returns false for different objects.
     */
    @Test
    @DisplayName("Equals Returns False for Different Objects")
    void testEqualsDifferent() {
        CalculationHistoryItem item2 = new CalculationHistoryItem();
        item.setInput1(1.0);
        item.setOperation("add");
        item.setInput2(2.0);
        item.setResult(3.0);
        item2.setInput1(2.0);
        item2.setOperation("add");
        item2.setInput2(2.0);
        item2.setResult(4.0);
        assertNotEquals(item, item2);
    }

    /**
     * Test equals with null and different class.
     */
    @Test
    @DisplayName("Equals with Null and Different Class")
    void testEqualsNullAndDifferentClass() {
        assertNotEquals(item, null);
        assertNotEquals(item, "string");
    }

    /**
     * Test toString returns expected format.
     */
    @Test
    @DisplayName("toString Returns Expected Format")
    void testToString() {
        item.setInput1(1.1);
        item.setOperation("multiply");
        item.setInput2(2.2);
        item.setResult(2.42);
        String str = item.toString();
        assertTrue(str.contains("input1=1.1"));
        assertTrue(str.contains("operation='multiply'"));
        assertTrue(str.contains("input2=2.2"));
        assertTrue(str.contains("result=2.42"));
    }

    /**
     * Test edge case: negative and zero values.
     */
    @Test
    @DisplayName("Edge Case: Negative and Zero Values")
    void testNegativeAndZeroValues() {
        item.setInput1(-5.0);
        item.setOperation("divide");
        item.setInput2(0.0);
        item.setResult(Double.NEGATIVE_INFINITY);
        assertEquals(-5.0, item.getInput1());
        assertEquals("divide", item.getOperation());
        assertEquals(0.0, item.getInput2());
        assertEquals(Double.NEGATIVE_INFINITY, item.getResult());
    }
}
