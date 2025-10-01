package com.example.flightsearch.util;

import com.example.flightsearch.entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaginationService.
 */
class PaginationServiceTest {
    private PaginationService paginationService;
    private List<Flight> flights;

    @BeforeEach
    void setUp() {
        paginationService = new PaginationService();
        flights = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Flight f = new Flight();
            f.setFlightId("F" + i);
            flights.add(f);
        }
    }

    /**
     * Test normal pagination.
     */
    @Test
    @DisplayName("paginateResults returns correct sublist for valid page and size")
    void testPaginateResults_Normal() {
        List<Flight> page1 = paginationService.paginateResults(flights, 1, 3);
        assertEquals(3, page1.size());
        assertEquals("F1", page1.get(0).getFlightId());
        assertEquals("F3", page1.get(2).getFlightId());
    }

    /**
     * Test pagination with page out of range.
     */
    @Test
    @DisplayName("paginateResults returns empty list for page out of range")
    void testPaginateResults_PageOutOfRange() {
        List<Flight> page5 = paginationService.paginateResults(flights, 5, 3);
        assertTrue(page5.isEmpty());
    }

    /**
     * Test pagination with null and empty list.
     */
    @Test
    @DisplayName("paginateResults returns empty list for null or empty input")
    void testPaginateResults_NullOrEmpty() {
        assertTrue(paginationService.paginateResults(null, 1, 3).isEmpty());
        assertTrue(paginationService.paginateResults(Collections.emptyList(), 1, 3).isEmpty());
    }

    /**
     * Test getTotalPages with normal and edge cases.
     */
    @Test
    @DisplayName("getTotalPages calculates pages correctly")
    void testGetTotalPages() {
        assertEquals(4, paginationService.getTotalPages(10, 3));
        assertEquals(1, paginationService.getTotalPages(10, 0)); // pageSize <= 0
        assertEquals(1, paginationService.getTotalPages(0, 5));
    }
}
