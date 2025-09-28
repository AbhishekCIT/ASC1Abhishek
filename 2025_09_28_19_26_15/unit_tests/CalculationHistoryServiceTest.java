package com.example.calculator.service;

import com.example.calculator.dto.CalculationHistoryItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for CalculationHistoryService.
 * Covers add, getAll, clear, duplicate prevention, and boundary conditions.
 */
class CalculationHistoryServiceTest {
    private CalculationHistoryService service;

    @BeforeEach
    void setUp() {
        service = new CalculationHistoryService();
        service.clear(); // Ensure clean state
    }

    /**
     * Test adding a calculation to history.
     */
    @Test
    @DisplayName("Add Calculation to History")
    void testAddCalculation() {
        CalculationHistoryItem item = new CalculationHistoryItem();
        item.setInput1(1.0);
        item.setOperation("add");
        item.setInput2(2.0);
        item.setResult(3.0);
        service.add(item);
        List<CalculationHistoryItem> history = service.getAll();
        assertEquals(1, history.size());
        assertEquals(item, history.get(0));
    }

    /**
     * Test duplicate calculation is not added.
     */
    @Test
    @DisplayName("Duplicate Calculation Not Added")
    void testDuplicateNotAdded() {
        CalculationHistoryItem item = new CalculationHistoryItem();
        item.setInput1(1.0);
        item.setOperation("add");
        item.setInput2(2.0);
        item.setResult(3.0);
        service.add(item);
        service.add(item); // Duplicate
        List<CalculationHistoryItem> history = service.getAll();
        assertEquals(1, history.size());
    }

    /**
     * Test history does not exceed max size (10).
     */
    @Test
    @DisplayName("History Does Not Exceed Max Size")
    void testMaxHistorySize() {
        for (int i = 0; i < 12; i++) {
            CalculationHistoryItem item = new CalculationHistoryItem();
            item.setInput1(i);
            item.setOperation("add");
            item.setInput2(i + 1);
            item.setResult(i + i + 1);
            service.add(item);
        }
        List<CalculationHistoryItem> history = service.getAll();
        assertEquals(10, history.size());
        assertEquals(2.0, history.get(0).getInput1()); // Oldest removed
    }

    /**
     * Test clear removes all history.
     */
    @Test
    @DisplayName("Clear Removes All History")
    void testClear() {
        CalculationHistoryItem item = new CalculationHistoryItem();
        item.setInput1(1.0);
        item.setOperation("add");
        item.setInput2(2.0);
        item.setResult(3.0);
        service.add(item);
        service.clear();
        assertTrue(service.getAll().isEmpty());
    }

    /**
     * Test getAll returns a copy, not the internal list.
     */
    @Test
    @DisplayName("getAll Returns Copy")
    void testGetAllReturnsCopy() {
        CalculationHistoryItem item = new CalculationHistoryItem();
        item.setInput1(1.0);
        item.setOperation("add");
        item.setInput2(2.0);
        item.setResult(3.0);
        service.add(item);
        List<CalculationHistoryItem> history = service.getAll();
        history.clear();
        assertEquals(1, service.getAll().size());
    }
}
