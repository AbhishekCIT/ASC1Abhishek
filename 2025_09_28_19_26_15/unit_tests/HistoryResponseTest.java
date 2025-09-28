package com.example.calculator.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for HistoryResponse DTO.
 * Covers getters, setters, and edge cases.
 */
class HistoryResponseTest {
    private HistoryResponse response;

    @BeforeEach
    void setUp() {
        response = new HistoryResponse();
    }

    /**
     * Test getter and setter for history list.
     */
    @Test
    @DisplayName("Getters and Setters Work Correctly")
    void testGettersAndSetters() {
        List<CalculationHistoryItem> history = new ArrayList<>();
        CalculationHistoryItem item = new CalculationHistoryItem();
        item.setInput1(1.0);
        item.setOperation("add");
        item.setInput2(2.0);
        item.setResult(3.0);
        history.add(item);
        response.setHistory(history);
        assertEquals(history, response.getHistory());
        assertEquals(1, response.getHistory().size());
    }

    /**
     * Test edge case: empty and null history list.
     */
    @Test
    @DisplayName("Edge Case: Empty and Null History List")
    void testEmptyAndNullHistory() {
        response.setHistory(Collections.emptyList());
        assertTrue(response.getHistory().isEmpty());
        response.setHistory(null);
        assertNull(response.getHistory());
    }
}
