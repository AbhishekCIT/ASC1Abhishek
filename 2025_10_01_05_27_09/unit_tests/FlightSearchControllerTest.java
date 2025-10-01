package com.example.flightsearch.controller;

import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

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
     * Test normal scenario: valid search request returns flights.
     */
    @Test
    @DisplayName("searchFlights returns OK response for valid request")
    void testSearchFlights_ValidRequest_ReturnsOk() {
        FlightSearchRequest request = new FlightSearchRequest();
        FlightSearchResponse response = new FlightSearchResponse();
        when(flightSearchService.findFlights(any(FlightSearchRequest.class))).thenReturn(response);

        ResponseEntity<?> result = flightSearchController.searchFlights(request);
        assertEquals(200, result.getStatusCodeValue());
        assertSame(response, result.getBody());
        verify(flightSearchService, times(1)).findFlights(request);
    }

    /**
     * Test error scenario: service throws exception, controller returns bad request.
     */
    @Test
    @DisplayName("searchFlights returns BadRequest when service throws exception")
    void testSearchFlights_ServiceThrowsException_ReturnsBadRequest() {
        FlightSearchRequest request = new FlightSearchRequest();
        when(flightSearchService.findFlights(any(FlightSearchRequest.class))).thenThrow(new RuntimeException("error"));

        ResponseEntity<?> result = flightSearchController.searchFlights(request);
        assertEquals(400, result.getStatusCodeValue());
        assertTrue(result.getBody() instanceof FlightSearchResponse);
        assertEquals("error", ((FlightSearchResponse) result.getBody()).getError());
        verify(flightSearchService, times(1)).findFlights(request);
    }

    /**
     * Test edge case: null request object.
     */
    @Test
    @DisplayName("searchFlights handles null request object")
    void testSearchFlights_NullRequest() {
        when(flightSearchService.findFlights(null)).thenThrow(new NullPointerException("request is null"));
        ResponseEntity<?> result = flightSearchController.searchFlights(null);
        assertEquals(400, result.getStatusCodeValue());
        assertTrue(result.getBody() instanceof FlightSearchResponse);
        assertEquals("request is null", ((FlightSearchResponse) result.getBody()).getError());
    }

    // Additional boundary and error-handling scenarios can be added as needed.
}
