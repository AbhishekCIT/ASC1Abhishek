package com.example.airtransport.controller;

import com.example.airtransport.model.Flight;
import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightController.
 */
class FlightControllerTest {
    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test searching for flights with valid input.
     */
    @Test
    @DisplayName("searchFlights returns list of flights for valid input")
    void testSearchFlights_ValidInput_ReturnsFlights() {
        Flight flight = new Flight("F123", "DEL", "BOM", "2025-10-01T10:00", "2025-10-01T13:00", 3, 300.00);
        List<Flight> flights = Collections.singletonList(flight);
        when(flightService.searchFlights(any(FlightSearchRequest.class))).thenReturn(flights);

        ResponseEntity<List<Flight>> response = flightController.searchFlights(
                "DEL", "BOM", "2025-10-01", null, 1);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("F123", response.getBody().get(0).getFlightId());
        verify(flightService, times(1)).searchFlights(any(FlightSearchRequest.class));
    }

    /**
     * Test searching for flights with no results (edge case).
     */
    @Test
    @DisplayName("searchFlights returns empty list when no flights found")
    void testSearchFlights_NoFlightsFound_ReturnsEmptyList() {
        when(flightService.searchFlights(any(FlightSearchRequest.class))).thenReturn(Collections.emptyList());

        ResponseEntity<List<Flight>> response = flightController.searchFlights(
                "DEL", "BOM", "2025-10-01", null, 1);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(flightService, times(1)).searchFlights(any(FlightSearchRequest.class));
    }

    /**
     * Test searching for flights with invalid airport code (error scenario).
     */
    @Test
    @DisplayName("searchFlights throws exception for invalid airport code")
    void testSearchFlights_InvalidAirportCode_ThrowsException() {
        when(flightService.searchFlights(any(FlightSearchRequest.class)))
                .thenThrow(new IllegalArgumentException("Invalid origin airport code"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            flightController.searchFlights("XX", "BOM", "2025-10-01", null, 1);
        });
        assertEquals("Invalid origin airport code", exception.getMessage());
        verify(flightService, times(1)).searchFlights(any(FlightSearchRequest.class));
    }

    /**
     * Test searching for flights with invalid date (error scenario).
     */
    @Test
    @DisplayName("searchFlights throws exception for invalid departure date")
    void testSearchFlights_InvalidDepartureDate_ThrowsException() {
        when(flightService.searchFlights(any(FlightSearchRequest.class)))
                .thenThrow(new IllegalArgumentException("Invalid departure date"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            flightController.searchFlights("DEL", "BOM", "2020-01-01", null, 1);
        });
        assertEquals("Invalid departure date", exception.getMessage());
        verify(flightService, times(1)).searchFlights(any(FlightSearchRequest.class));
    }
}
