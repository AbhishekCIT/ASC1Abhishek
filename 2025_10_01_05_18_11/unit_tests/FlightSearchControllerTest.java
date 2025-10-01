package com.example.flightsearch.controller;

import com.example.flightsearch.exception.ExternalAPIException;
import com.example.flightsearch.exception.InvalidInputException;
import com.example.flightsearch.exception.NoFlightsFoundException;
import com.example.flightsearch.exception.RateLimitExceededException;
import com.example.flightsearch.model.ErrorResponse;
import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.service.FlightSearchService;
import com.example.flightsearch.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightSearchController.
 */
class FlightSearchControllerTest {

    @Mock
    private FlightSearchService flightSearchService;

    @Mock
    private ValidationUtil validationUtil;

    @InjectMocks
    private FlightSearchController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal scenario: valid request returns FlightSearchResponse.
     */
    @Test
    @DisplayName("searchFlights returns OK for valid request")
    void testSearchFlights_NormalScenario() {
        FlightSearchRequest request = new FlightSearchRequest();
        FlightSearchResponse response = new FlightSearchResponse();
        doNothing().when(validationUtil).validateSearchParams(request);
        when(flightSearchService.searchFlights(request)).thenReturn(response);
        ResponseEntity<?> result = controller.searchFlights(request);
        assertEquals(HttpStatus.OK, result.getStatusCode(), "Should return 200 OK");
        assertSame(response, result.getBody(), "Response body should be FlightSearchResponse");
    }

    /**
     * Test error scenario: InvalidInputException returns BAD_REQUEST.
     */
    @Test
    @DisplayName("searchFlights returns BAD_REQUEST for invalid input")
    void testSearchFlights_InvalidInput() {
        FlightSearchRequest request = new FlightSearchRequest();
        doThrow(new InvalidInputException("Invalid input")).when(validationUtil).validateSearchParams(request);
        ResponseEntity<?> result = controller.searchFlights(request);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode(), "Should return 400 Bad Request");
        assertTrue(result.getBody() instanceof ErrorResponse, "Body should be ErrorResponse");
        assertEquals("Invalid input", ((ErrorResponse) result.getBody()).getMessage());
    }

    /**
     * Test error scenario: NoFlightsFoundException returns NOT_FOUND.
     */
    @Test
    @DisplayName("searchFlights returns NOT_FOUND when no flights found")
    void testSearchFlights_NoFlightsFound() {
        FlightSearchRequest request = new FlightSearchRequest();
        doNothing().when(validationUtil).validateSearchParams(request);
        when(flightSearchService.searchFlights(request)).thenThrow(new NoFlightsFoundException("No flights found"));
        ResponseEntity<?> result = controller.searchFlights(request);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode(), "Should return 404 Not Found");
        assertTrue(result.getBody() instanceof ErrorResponse, "Body should be ErrorResponse");
        assertEquals("No flights found", ((ErrorResponse) result.getBody()).getMessage());
    }

    /**
     * Test error scenario: ExternalAPIException returns BAD_GATEWAY.
     */
    @Test
    @DisplayName("searchFlights returns BAD_GATEWAY for external API failure")
    void testSearchFlights_ExternalAPIException() {
        FlightSearchRequest request = new FlightSearchRequest();
        doNothing().when(validationUtil).validateSearchParams(request);
        when(flightSearchService.searchFlights(request)).thenThrow(new ExternalAPIException("API error"));
        ResponseEntity<?> result = controller.searchFlights(request);
        assertEquals(HttpStatus.BAD_GATEWAY, result.getStatusCode(), "Should return 502 Bad Gateway");
        assertTrue(result.getBody() instanceof ErrorResponse, "Body should be ErrorResponse");
        assertEquals("Failed to fetch data from airline provider.", ((ErrorResponse) result.getBody()).getMessage());
    }

    /**
     * Test error scenario: RateLimitExceededException returns TOO_MANY_REQUESTS.
     */
    @Test
    @DisplayName("searchFlights returns TOO_MANY_REQUESTS for rate limit exceeded")
    void testSearchFlights_RateLimitExceeded() {
        FlightSearchRequest request = new FlightSearchRequest();
        doNothing().when(validationUtil).validateSearchParams(request);
        when(flightSearchService.searchFlights(request)).thenThrow(new RateLimitExceededException("Rate limit exceeded"));
        ResponseEntity<?> result = controller.searchFlights(request);
        assertEquals(HttpStatus.TOO_MANY_REQUESTS, result.getStatusCode(), "Should return 429 Too Many Requests");
        assertTrue(result.getBody() instanceof ErrorResponse, "Body should be ErrorResponse");
        assertEquals("Too many requests. Please try again later.", ((ErrorResponse) result.getBody()).getMessage());
    }
}
