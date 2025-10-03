package com.example.airbooking.controller;

import com.example.airbooking.model.Flight;
import com.example.airbooking.service.FlightInventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightController.
 */
public class FlightControllerTest {
    @Mock
    private FlightInventoryService flightInventoryService;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal search scenario.
     */
    @Test
    void testSearchFlights_Success() {
        List<Flight> flights = Arrays.asList(new Flight(), new Flight());
        when(flightInventoryService.getAvailableFlights("JFK", "LAX", "2025-10-10", 2)).thenReturn(flights);
        ResponseEntity<List<Flight>> response = flightController.searchFlights("JFK", "LAX", "2025-10-10", 2);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(flights, response.getBody());
    }

    /**
     * Test search with missing origin (edge case).
     */
    @Test
    void testSearchFlights_MissingOrigin() {
        ResponseEntity<List<Flight>> response = flightController.searchFlights("", "LAX", "2025-10-10", 2);
        assertEquals(400, response.getStatusCodeValue());
    }

    /**
     * Test search with missing destination (edge case).
     */
    @Test
    void testSearchFlights_MissingDestination() {
        ResponseEntity<List<Flight>> response = flightController.searchFlights("JFK", "", "2025-10-10", 2);
        assertEquals(400, response.getStatusCodeValue());
    }

    /**
     * Test search with missing date (edge case).
     */
    @Test
    void testSearchFlights_MissingDate() {
        ResponseEntity<List<Flight>> response = flightController.searchFlights("JFK", "LAX", "", 2);
        assertEquals(400, response.getStatusCodeValue());
    }

    /**
     * Test search with no flights found (boundary case).
     */
    @Test
    void testSearchFlights_NoFlightsFound() {
        when(flightInventoryService.getAvailableFlights("JFK", "LAX", "2025-10-10", 2)).thenReturn(Collections.emptyList());
        ResponseEntity<List<Flight>> response = flightController.searchFlights("JFK", "LAX", "2025-10-10", 2);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }

    /**
     * Test getFlightById normal scenario.
     */
    @Test
    void testGetFlightById_Success() {
        Flight flight = new Flight();
        when(flightInventoryService.getFlightById(1L)).thenReturn(flight);
        ResponseEntity<Flight> response = flightController.getFlightById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(flight, response.getBody());
    }

    /**
     * Test getFlightById not found scenario.
     */
    @Test
    void testGetFlightById_NotFound() {
        when(flightInventoryService.getFlightById(1L)).thenReturn(null);
        ResponseEntity<Flight> response = flightController.getFlightById(1L);
        assertEquals(404, response.getStatusCodeValue());
    }
}
