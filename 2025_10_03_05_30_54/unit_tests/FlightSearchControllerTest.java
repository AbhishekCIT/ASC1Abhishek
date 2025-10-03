package com.example.airtransport.controller;

import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.model.FlightSearchResponse;
import com.example.airtransport.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for FlightSearchController.
 * Covers normal, edge, boundary, and error-handling scenarios for searchFlights endpoint.
 */
public class FlightSearchControllerTest {
    @Mock
    private FlightSearchService flightSearchService;

    @InjectMocks
    private FlightSearchController flightSearchController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal scenario: valid search request returns OK response.
     */
    @Test
    void testSearchFlights_Success() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LHR");
        request.setDepartureDate("2099-12-31");
        FlightSearchResponse response = new FlightSearchResponse();
        when(flightSearchService.searchFlights(request)).thenReturn(response);

        ResponseEntity<?> result = flightSearchController.searchFlights(request);
        assertEquals(200, result.getStatusCodeValue());
        assertSame(response, result.getBody());
    }

    /**
     * Test error scenario: invalid input triggers IllegalArgumentException and returns BAD_REQUEST.
     */
    @Test
    void testSearchFlights_InvalidInput() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("JFK"); // Invalid: same as origin
        request.setDepartureDate("2099-12-31");
        when(flightSearchService.searchFlights(request)).thenThrow(new IllegalArgumentException("Origin and destination cannot be the same."));

        ResponseEntity<?> result = flightSearchController.searchFlights(request);
        assertEquals(400, result.getStatusCodeValue());
        assertTrue(result.getBody() instanceof FlightSearchController.ErrorResponse);
        assertEquals("Origin and destination cannot be the same.", ((FlightSearchController.ErrorResponse) result.getBody()).getError());
    }

    /**
     * Test error scenario: unexpected exception returns INTERNAL_SERVER_ERROR.
     */
    @Test
    void testSearchFlights_InternalServerError() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LHR");
        request.setDepartureDate("2099-12-31");
        when(flightSearchService.searchFlights(request)).thenThrow(new RuntimeException("Database down"));

        ResponseEntity<?> result = flightSearchController.searchFlights(request);
        assertEquals(500, result.getStatusCodeValue());
        assertTrue(result.getBody() instanceof FlightSearchController.ErrorResponse);
        assertEquals("Internal server error", ((FlightSearchController.ErrorResponse) result.getBody()).getError());
    }
}
