package com.airtransport.controller;

import com.airtransport.model.Flight;
import com.airtransport.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for FlightSearchController.
 * Covers normal, edge, and error scenarios for flight search endpoint.
 */
public class FlightSearchControllerTest {

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightSearchController flightSearchController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test searchFlights with valid input.
     * Purpose: Should return list of flights.
     */
    @Test
    void testSearchFlights_Success() {
        LocalDateTime date = LocalDateTime.now();
        String destination = "JFK";
        List<Flight> mockFlights = List.of(new Flight());
        when(flightService.searchFlights(destination, date)).thenReturn(mockFlights);
        List<Flight> result = flightSearchController.searchFlights(date, destination, 1);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(flightService, times(1)).searchFlights(destination, date);
    }

    /**
     * Test searchFlights with empty result (edge case).
     * Purpose: Should return empty list.
     */
    @Test
    void testSearchFlights_EmptyResult() {
        LocalDateTime date = LocalDateTime.now();
        String destination = "LAX";
        when(flightService.searchFlights(destination, date)).thenReturn(Collections.emptyList());
        List<Flight> result = flightSearchController.searchFlights(date, destination, 2);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Test searchFlights with null destination (error case).
     * Purpose: Should throw IllegalArgumentException from service.
     */
    @Test
    void testSearchFlights_NullDestination() {
        LocalDateTime date = LocalDateTime.now();
        when(flightService.searchFlights(null, date)).thenThrow(new IllegalArgumentException("Destination is required"));
        assertThrows(IllegalArgumentException.class, () -> flightSearchController.searchFlights(date, null, 1));
    }

    /**
     * Test searchFlights with null date (error case).
     * Purpose: Should throw IllegalArgumentException from service.
     */
    @Test
    void testSearchFlights_NullDate() {
        String destination = "JFK";
        when(flightService.searchFlights(destination, null)).thenThrow(new IllegalArgumentException("Date is required"));
        assertThrows(IllegalArgumentException.class, () -> flightSearchController.searchFlights(null, destination, 1));
    }
}
