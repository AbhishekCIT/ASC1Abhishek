package com.example.flightsearch.util;

import com.example.flightsearch.entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FilterService.
 */
class FilterServiceTest {
    private FilterService filterService;
    private List<Flight> flights;

    @BeforeEach
    void setUp() {
        filterService = new FilterService();
        flights = new ArrayList<>();
        Flight f1 = new Flight(); f1.setAirline("Delta"); f1.setPrice(200); f1.setDuration("2h");
        Flight f2 = new Flight(); f2.setAirline("United"); f2.setPrice(150); f2.setDuration("3h");
        Flight f3 = new Flight(); f3.setAirline("Delta"); f3.setPrice(300); f3.setDuration("1h 30m");
        flights.add(f1); flights.add(f2); flights.add(f3);
    }

    /**
     * Test applyFilters returns original list if filters is null.
     */
    @Test
    @DisplayName("applyFilters returns original list if filters is null")
    void testApplyFilters_NullFilters() {
        List<Flight> result = filterService.applyFilters(flights, null);
        assertEquals(flights, result);
    }

    /**
     * Test filter by airline.
     */
    @Test
    @DisplayName("applyFilters filters by airline")
    void testApplyFilters_FilterByAirline() {
        Map<String, String> filters = new HashMap<>();
        filters.put("airline", "Delta");
        List<Flight> result = filterService.applyFilters(flights, filters);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(f -> f.getAirline().equalsIgnoreCase("Delta")));
    }

    /**
     * Test sort by price ascending.
     */
    @Test
    @DisplayName("applyFilters sorts by price ascending")
    void testApplyFilters_SortByPriceAsc() {
        Map<String, String> filters = new HashMap<>();
        filters.put("price", "asc");
        List<Flight> result = filterService.applyFilters(flights, filters);
        assertEquals(3, result.size());
        assertEquals(150, result.get(0).getPrice());
        assertEquals(200, result.get(1).getPrice());
        assertEquals(300, result.get(2).getPrice());
    }

    /**
     * Test sort by price descending.
     */
    @Test
    @DisplayName("applyFilters sorts by price descending")
    void testApplyFilters_SortByPriceDesc() {
        Map<String, String> filters = new HashMap<>();
        filters.put("price", "desc");
        List<Flight> result = filterService.applyFilters(flights, filters);
        assertEquals(3, result.size());
        assertEquals(300, result.get(0).getPrice());
        assertEquals(200, result.get(1).getPrice());
        assertEquals(150, result.get(2).getPrice());
    }

    /**
     * Test sort by duration shortest.
     */
    @Test
    @DisplayName("applyFilters sorts by duration shortest")
    void testApplyFilters_SortByDurationShortest() {
        Map<String, String> filters = new HashMap<>();
        filters.put("duration", "shortest");
        List<Flight> result = filterService.applyFilters(flights, filters);
        assertEquals(3, result.size());
        assertEquals("1h 30m", result.get(0).getDuration());
        assertEquals("2h", result.get(1).getDuration());
        assertEquals("3h", result.get(2).getDuration());
    }

    /**
     * Test sort by duration longest.
     */
    @Test
    @DisplayName("applyFilters sorts by duration longest")
    void testApplyFilters_SortByDurationLongest() {
        Map<String, String> filters = new HashMap<>();
        filters.put("duration", "longest");
        List<Flight> result = filterService.applyFilters(flights, filters);
        assertEquals(3, result.size());
        assertEquals("3h", result.get(0).getDuration());
        assertEquals("2h", result.get(1).getDuration());
        assertEquals("1h 30m", result.get(2).getDuration());
    }

    /**
     * Test multiple filters applied together.
     */
    @Test
    @DisplayName("applyFilters applies multiple filters")
    void testApplyFilters_MultipleFilters() {
        Map<String, String> filters = new HashMap<>();
        filters.put("airline", "Delta");
        filters.put("price", "asc");
        List<Flight> result = filterService.applyFilters(flights, filters);
        assertEquals(2, result.size());
        assertTrue(result.get(0).getPrice() <= result.get(1).getPrice());
    }
}
