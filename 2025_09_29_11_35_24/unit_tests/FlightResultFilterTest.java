package com.airtransport.filter;

import com.airtransport.model.Flight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightResultFilter.
 */
public class FlightResultFilterTest {
    private FlightResultFilter filter;
    private List<Flight> flights;

    @BeforeEach
    void setUp() {
        filter = new FlightResultFilter();
        flights = Arrays.asList(
                new Flight("Delta", "DL123", "10:00", "13:00", "3h", 450),
                new Flight("United", "UA456", "11:00", "14:30", "3h 30m", 470),
                new Flight("Delta", "DL789", "15:00", "18:00", "3h", 500)
        );
    }

    @AfterEach
    void tearDown() {
        filter = null;
        flights = null;
    }

    @Test
    @DisplayName("applyFilters returns all flights when filters are null (edge case)")
    void testApplyFilters_NullFilters() {
        List<Flight> result = filter.applyFilters(flights, null);
        assertEquals(flights, result);
    }

    @Test
    @DisplayName("applyFilters returns all flights when filters are empty (edge case)")
    void testApplyFilters_EmptyFilters() {
        List<Flight> result = filter.applyFilters(flights, new HashMap<>());
        assertEquals(flights, result);
    }

    @Test
    @DisplayName("applyFilters filters by airline (normal scenario)")
    void testApplyFilters_FilterByAirline() {
        Map<String, Object> filters = new HashMap<>();
        filters.put("airline", "Delta");
        List<Flight> result = filter.applyFilters(flights, filters);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(f -> f.getAirline().equals("Delta")));
    }

    @Test
    @DisplayName("applyFilters filters by maxPrice (normal scenario)")
    void testApplyFilters_FilterByMaxPrice() {
        Map<String, Object> filters = new HashMap<>();
        filters.put("maxPrice", 460);
        List<Flight> result = filter.applyFilters(flights, filters);
        assertEquals(1, result.size());
        assertEquals("Delta", result.get(0).getAirline());
        assertEquals(450, result.get(0).getPrice());
    }

    @Test
    @DisplayName("applyFilters filters by airline and maxPrice (combined filters)")
    void testApplyFilters_CombinedFilters() {
        Map<String, Object> filters = new HashMap<>();
        filters.put("airline", "Delta");
        filters.put("maxPrice", 460);
        List<Flight> result = filter.applyFilters(flights, filters);
        assertEquals(1, result.size());
        assertEquals("Delta", result.get(0).getAirline());
        assertEquals(450, result.get(0).getPrice());
    }

    @Test
    @DisplayName("applyFilters returns empty list when no flights match (boundary condition)")
    void testApplyFilters_NoMatch() {
        Map<String, Object> filters = new HashMap<>();
        filters.put("airline", "NonExistent");
        List<Flight> result = filter.applyFilters(flights, filters);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("applyFilters handles empty flights list (edge case)")
    void testApplyFilters_EmptyFlightsList() {
        Map<String, Object> filters = new HashMap<>();
        filters.put("airline", "Delta");
        List<Flight> result = filter.applyFilters(Collections.emptyList(), filters);
        assertTrue(result.isEmpty());
    }
}
