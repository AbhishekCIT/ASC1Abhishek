package com.example.airlinebooking.controller;

import com.example.airlinebooking.model.Flight;
import com.example.airlinebooking.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightSearchController.
 */
class FlightSearchControllerTest {
    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightSearchController flightSearchController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test searching for flights returns a list.
     */
    @Test
    void testSearchFlights_Success() {
        List<Flight> flights = Arrays.asList(new Flight(), new Flight());
        when(flightService.searchFlights(anyString(), anyString(), anyString())).thenReturn(flights);
        ResponseEntity<List<Flight>> response = flightSearchController.searchFlights("JFK", "LAX", "2025-10-01");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(flights, response.getBody());
    }

    /**
     * Test searching for flights with empty result.
     */
    @Test
    void testSearchFlights_EmptyResult() {
        when(flightService.searchFlights(anyString(), anyString(), anyString())).thenReturn(Arrays.asList());
        ResponseEntity<List<Flight>> response = flightSearchController.searchFlights("JFK", "LAX", "2025-10-01");
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }
}
