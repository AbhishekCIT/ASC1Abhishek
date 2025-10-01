package com.example.airbooking.controller;

import com.example.airbooking.dto.FlightSearchRequest;
import com.example.airbooking.dto.FlightSearchResponse;
import com.example.airbooking.service.FlightSearchService;
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
 * JUnit tests for FlightSearchController.
 * Covers normal, edge, boundary, and error-handling scenarios for flight search endpoint.
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
     * Test searching flights with valid input (normal scenario).
     */
    @Test
    @DisplayName("searchFlights: should return list of flights for valid request")
    void searchFlights_validRequest_returnsFlights() {
        FlightSearchRequest request = new FlightSearchRequest();
        List<FlightSearchResponse> expected = new ArrayList<>();
        expected.add(new FlightSearchResponse());
        when(flightSearchService.searchFlights(request)).thenReturn(expected);

        ResponseEntity<List<FlightSearchResponse>> response = flightSearchController.searchFlights(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expected, response.getBody());
        verify(flightSearchService).searchFlights(request);
    }

    /**
     * Test searching flights with no results (edge case).
     */
    @Test
    @DisplayName("searchFlights: should return empty list when no flights found")
    void searchFlights_noFlights_returnsEmptyList() {
        FlightSearchRequest request = new FlightSearchRequest();
        when(flightSearchService.searchFlights(request)).thenReturn(Collections.emptyList());

        ResponseEntity<List<FlightSearchResponse>> response = flightSearchController.searchFlights(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
        verify(flightSearchService).searchFlights(request);
    }

    /**
     * Test searching flights with null request (edge case).
     */
    @Test
    @DisplayName("searchFlights: should throw NullPointerException for null request")
    void searchFlights_nullRequest_throwsException() {
        assertThrows(NullPointerException.class, () -> flightSearchController.searchFlights(null));
    }

    /**
     * Test searching flights when service throws exception (error scenario).
     */
    @Test
    @DisplayName("searchFlights: should propagate exception from service")
    void searchFlights_serviceThrowsException_propagates() {
        FlightSearchRequest request = new FlightSearchRequest();
        when(flightSearchService.searchFlights(request)).thenThrow(new RuntimeException("Service error"));

        Exception ex = assertThrows(RuntimeException.class, () -> flightSearchController.searchFlights(request));
        assertEquals("Service error", ex.getMessage());
        verify(flightSearchService).searchFlights(request);
    }
}
