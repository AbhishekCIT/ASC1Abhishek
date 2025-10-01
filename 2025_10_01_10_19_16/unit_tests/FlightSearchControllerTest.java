package com.airtransport.controller;

import com.airtransport.model.Flight;
import com.airtransport.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
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
     * Test successful flight search scenario.
     */
    @Test
    void testSearchFlights_Success() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("F123", "Delta", "10:00", "16:00", "JFK", "LAX", 350.00, 20));
        when(flightService.searchFlights(anyString(), anyString(), anyString())).thenReturn(flights);

        ResponseEntity<?> response = flightSearchController.searchFlights("JFK", "LAX", "2025-12-01");
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof FlightSearchController.FlightSearchResponse);
        FlightSearchController.FlightSearchResponse resp = (FlightSearchController.FlightSearchResponse) response.getBody();
        assertEquals(1, resp.getFlights().size());
        assertEquals("F123", resp.getFlights().get(0).getFlightId());
    }

    /**
     * Test flight search with invalid arguments (IllegalArgumentException).
     */
    @Test
    void testSearchFlights_InvalidArgument() {
        when(flightService.searchFlights(anyString(), anyString(), anyString())).thenThrow(new IllegalArgumentException("Invalid origin airport code"));
        ResponseEntity<?> response = flightSearchController.searchFlights("", "LAX", "2025-12-01");
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof FlightSearchController.ErrorResponse);
        FlightSearchController.ErrorResponse resp = (FlightSearchController.ErrorResponse) response.getBody();
        assertEquals("Invalid origin airport code", resp.getError());
    }
}
