package com.example.airline.controller;

import com.example.airline.dto.FlightSearchRequest;
import com.example.airline.dto.FlightSearchResponse;
import com.example.airline.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightSearchController.
 * Covers normal, edge, boundary, and error-handling scenarios for flight search API.
 */
class FlightSearchControllerTest {

    @Mock
    private FlightSearchService flightSearchService;
    @InjectMocks
    private FlightSearchController flightSearchController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test searching flights with valid parameters (normal scenario).
     */
    @Test
    @DisplayName("searchFlights: should return list of flights for valid input")
    void searchFlights_validInput_returnsFlights() {
        String origin = "JFK";
        String destination = "LAX";
        String date = "2025-10-01";
        List<FlightSearchResponse> expectedFlights = new ArrayList<>();
        expectedFlights.add(new FlightSearchResponse());
        when(flightSearchService.searchFlights(any(FlightSearchRequest.class))).thenReturn(expectedFlights);

        ResponseEntity<List<FlightSearchResponse>> response = flightSearchController.searchFlights(origin, destination, date);

        assertNotNull(response);
        assertEquals(expectedFlights, response.getBody());
        verify(flightSearchService, times(1)).searchFlights(any(FlightSearchRequest.class));
    }

    /**
     * Test searching flights with empty result (edge case).
     */
    @Test
    @DisplayName("searchFlights: should return empty list if no flights found")
    void searchFlights_noFlights_returnsEmptyList() {
        String origin = "JFK";
        String destination = "LAX";
        String date = "2025-10-01";
        when(flightSearchService.searchFlights(any(FlightSearchRequest.class))).thenReturn(Collections.emptyList());

        ResponseEntity<List<FlightSearchResponse>> response = flightSearchController.searchFlights(origin, destination, date);

        assertNotNull(response);
        assertTrue(response.getBody().isEmpty());
    }

    /**
     * Test searching flights with invalid (empty) origin (boundary/validation case).
     */
    @Test
    @DisplayName("searchFlights: should handle empty origin input")
    void searchFlights_emptyOrigin_returnsFlights() {
        String origin = "";
        String destination = "LAX";
        String date = "2025-10-01";
        when(flightSearchService.searchFlights(any(FlightSearchRequest.class))).thenReturn(Collections.emptyList());

        ResponseEntity<List<FlightSearchResponse>> response = flightSearchController.searchFlights(origin, destination, date);

        assertNotNull(response);
        assertTrue(response.getBody().isEmpty());
    }

    /**
     * Test searching flights with invalid (empty) destination (boundary/validation case).
     */
    @Test
    @DisplayName("searchFlights: should handle empty destination input")
    void searchFlights_emptyDestination_returnsFlights() {
        String origin = "JFK";
        String destination = "";
        String date = "2025-10-01";
        when(flightSearchService.searchFlights(any(FlightSearchRequest.class))).thenReturn(Collections.emptyList());

        ResponseEntity<List<FlightSearchResponse>> response = flightSearchController.searchFlights(origin, destination, date);

        assertNotNull(response);
        assertTrue(response.getBody().isEmpty());
    }

    /**
     * Test searching flights with invalid (past) date (boundary/validation case).
     */
    @Test
    @DisplayName("searchFlights: should handle past date input")
    void searchFlights_pastDate_returnsFlights() {
        String origin = "JFK";
        String destination = "LAX";
        String date = "2020-01-01";
        when(flightSearchService.searchFlights(any(FlightSearchRequest.class))).thenReturn(Collections.emptyList());

        ResponseEntity<List<FlightSearchResponse>> response = flightSearchController.searchFlights(origin, destination, date);

        assertNotNull(response);
        assertTrue(response.getBody().isEmpty());
    }

    /**
     * Test searching flights when FlightSearchService throws exception (error-handling scenario).
     */
    @Test
    @DisplayName("searchFlights: should propagate exception from FlightSearchService")
    void searchFlights_serviceThrowsException_propagates() {
        String origin = "JFK";
        String destination = "LAX";
        String date = "2025-10-01";
        when(flightSearchService.searchFlights(any(FlightSearchRequest.class))).thenThrow(new RuntimeException("Service error"));
        assertThrows(RuntimeException.class, () -> flightSearchController.searchFlights(origin, destination, date));
    }
}
