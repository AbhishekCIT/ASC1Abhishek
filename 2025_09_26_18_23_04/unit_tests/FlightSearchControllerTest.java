package com.example.flightsearch.controller;

import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightSearchController.
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
     * Test normal scenario: flights found.
     */
    @Test
    void testSearchFlights_FlightsFound() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengers(1);
        request.setFlightClass("Economy");

        FlightSearchResponse resp = new FlightSearchResponse();
        resp.setFlightId(1L);
        resp.setAirline("Delta");
        List<FlightSearchResponse> flights = Arrays.asList(resp);

        when(flightSearchService.searchFlights(any(FlightSearchRequest.class))).thenReturn(flights);

        ResponseEntity<?> response = flightSearchController.searchFlights(request);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof List);
        verify(flightSearchService, times(1)).searchFlights(any(FlightSearchRequest.class));
    }

    /**
     * Test edge case: no flights found.
     */
    @Test
    void testSearchFlights_NoFlightsFound() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengers(1);
        request.setFlightClass("Economy");

        when(flightSearchService.searchFlights(any(FlightSearchRequest.class))).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = flightSearchController.searchFlights(request);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("No flights found matching criteria.", response.getBody());
    }

    /**
     * Test error scenario: validation fails (IllegalArgumentException).
     */
    @Test
    void testSearchFlights_ValidationError() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("JFK"); // Invalid: same as origin
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengers(1);
        request.setFlightClass("Economy");

        when(flightSearchService.searchFlights(any(FlightSearchRequest.class)))
                .thenThrow(new IllegalArgumentException("Origin and destination cannot be the same; "));

        ResponseEntity<?> response = flightSearchController.searchFlights(request);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Validation failed"));
    }

    /**
     * Test error scenario: unexpected exception (500).
     */
    @Test
    void testSearchFlights_InternalServerError() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengers(1);
        request.setFlightClass("Economy");

        when(flightSearchService.searchFlights(any(FlightSearchRequest.class)))
                .thenThrow(new RuntimeException("Database down"));

        ResponseEntity<?> response = flightSearchController.searchFlights(request);
        assertEquals(500, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Internal server error"));
    }

    /**
     * Test boundary condition: minimum passengers (1).
     */
    @Test
    void testSearchFlights_MinimumPassengers() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengers(1);
        request.setFlightClass("Economy");

        FlightSearchResponse resp = new FlightSearchResponse();
        resp.setFlightId(2L);
        resp.setAirline("United");
        List<FlightSearchResponse> flights = Arrays.asList(resp);

        when(flightSearchService.searchFlights(any(FlightSearchRequest.class))).thenReturn(flights);

        ResponseEntity<?> response = flightSearchController.searchFlights(request);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof List);
    }

    /**
     * Test boundary condition: travel date is today (should fail).
     */
    @Test
    void testSearchFlights_TravelDateToday() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now());
        request.setPassengers(1);
        request.setFlightClass("Economy");

        when(flightSearchService.searchFlights(any(FlightSearchRequest.class)))
                .thenThrow(new IllegalArgumentException("Travel date cannot be in the past; "));

        ResponseEntity<?> response = flightSearchController.searchFlights(request);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Validation failed"));
    }
}
