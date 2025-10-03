package com.example.airbooking.repository;

import com.example.airbooking.model.Flight;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightRepository interface (mock-based).
 */
public class FlightRepositoryTest {
    /**
     * Test findAvailableFlights returns flights when found.
     */
    @Test
    void testFindAvailableFlights_Found() {
        FlightRepository repo = mock(FlightRepository.class);
        List<Flight> flights = Arrays.asList(new Flight(), new Flight());
        when(repo.findAvailableFlights("JFK", "LAX", "2025-10-10", 2)).thenReturn(flights);
        assertEquals(flights, repo.findAvailableFlights("JFK", "LAX", "2025-10-10", 2));
    }

    /**
     * Test findAvailableFlights returns empty list when none found.
     */
    @Test
    void testFindAvailableFlights_Empty() {
        FlightRepository repo = mock(FlightRepository.class);
        when(repo.findAvailableFlights("JFK", "LAX", "2025-10-10", 2)).thenReturn(Arrays.asList());
        assertTrue(repo.findAvailableFlights("JFK", "LAX", "2025-10-10", 2).isEmpty());
    }
}
