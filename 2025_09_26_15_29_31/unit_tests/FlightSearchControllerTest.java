package com.example.flightsearch.controller;

import com.example.flightsearch.exception.ExternalAPIException;
import com.example.flightsearch.exception.FilterMismatchException;
import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightSearchController.
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

    @Test
    @DisplayName("Should return OK response with flight search results")
    // Tests normal scenario where search is successful
    void testSearchFlights_Success() {
        FlightSearchRequest request = new FlightSearchRequest();
        FlightSearchResponse response = new FlightSearchResponse();
        when(flightSearchService.searchFlights(any(FlightSearchRequest.class))).thenReturn(response);

        ResponseEntity<?> result = flightSearchController.searchFlights(request);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(response, result.getBody());
    }

    @Test
    @DisplayName("Should return BAD_REQUEST for validation errors")
    // Tests scenario where IllegalArgumentException is thrown (validation error)
    void testSearchFlights_ValidationError() {
        FlightSearchRequest request = new FlightSearchRequest();
        when(flightSearchService.searchFlights(any(FlightSearchRequest.class)))
                .thenThrow(new IllegalArgumentException("Invalid input"));

        ResponseEntity<?> result = flightSearchController.searchFlights(request);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertTrue(result.getBody() instanceof FlightSearchController.ErrorResponse);
        assertEquals("Invalid input", ((FlightSearchController.ErrorResponse) result.getBody()).getError());
    }

    @Test
    @DisplayName("Should return SERVICE_UNAVAILABLE for external API errors")
    // Tests scenario where ExternalAPIException is thrown
    void testSearchFlights_ExternalAPIError() {
        FlightSearchRequest request = new FlightSearchRequest();
        when(flightSearchService.searchFlights(any(FlightSearchRequest.class)))
                .thenThrow(new ExternalAPIException("API down"));

        ResponseEntity<?> result = flightSearchController.searchFlights(request);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, result.getStatusCode());
        assertTrue(result.getBody() instanceof FlightSearchController.ErrorResponse);
        assertEquals("Unable to retrieve flights at this time.", ((FlightSearchController.ErrorResponse) result.getBody()).getError());
    }

    @Test
    @DisplayName("Should return NOT_FOUND for filter mismatch errors")
    // Tests scenario where FilterMismatchException is thrown
    void testSearchFlights_FilterMismatchError() {
        FlightSearchRequest request = new FlightSearchRequest();
        when(flightSearchService.searchFlights(any(FlightSearchRequest.class)))
                .thenThrow(new FilterMismatchException("No flights found"));

        ResponseEntity<?> result = flightSearchController.searchFlights(request);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertTrue(result.getBody() instanceof FlightSearchController.ErrorResponse);
        assertEquals("No flights found for the given criteria.", ((FlightSearchController.ErrorResponse) result.getBody()).getError());
    }

    @Test
    @DisplayName("Should return INTERNAL_SERVER_ERROR for generic exceptions")
    // Tests scenario where a generic Exception is thrown
    void testSearchFlights_GenericError() {
        FlightSearchRequest request = new FlightSearchRequest();
        when(flightSearchService.searchFlights(any(FlightSearchRequest.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<?> result = flightSearchController.searchFlights(request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertTrue(result.getBody() instanceof FlightSearchController.ErrorResponse);
        assertEquals("An unexpected error occurred.", ((FlightSearchController.ErrorResponse) result.getBody()).getError());
    }
}
