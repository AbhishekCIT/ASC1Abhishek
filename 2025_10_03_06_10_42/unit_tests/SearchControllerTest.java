package com.airtransport.controller;

import com.airtransport.dto.SearchRequest;
import com.airtransport.dto.SearchResponse;
import com.airtransport.exception.InvalidLocationException;
import com.airtransport.exception.PastDateException;
import com.airtransport.exception.ProviderAPIException;
import com.airtransport.exception.ValidationException;
import com.airtransport.service.FlightProviderService;
import com.airtransport.service.LocationService;
import com.airtransport.service.SearchFilterService;
import com.airtransport.util.ErrorHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SearchController.
 * Covers normal, edge, boundary, and error-handling scenarios for searchFlights().
 */
class SearchControllerTest {
    @Mock
    private LocationService locationService;
    @Mock
    private FlightProviderService flightProviderService;
    @Mock
    private SearchFilterService searchFilterService;
    @Mock
    private ErrorHandler errorHandler;
    @InjectMocks
    private SearchController searchController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal search scenario with valid request.
     */
    @Test
    @DisplayName("searchFlights: should return SearchResponse for valid request")
    void testSearchFlights_Success() throws Exception {
        SearchRequest request = new SearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1).toString());
        request.setPreferences(Collections.emptyMap());
        when(locationService.validateLocation("JFK")).thenReturn(true);
        when(locationService.validateLocation("LAX")).thenReturn(true);
        List<Object> providerResults = List.of(new Object());
        when(flightProviderService.queryProviders(any(SearchRequest.class))).thenReturn(providerResults);
        when(searchFilterService.applyFilters(providerResults, request.getPreferences())).thenReturn(providerResults);

        ResponseEntity<?> result = searchController.searchFlights(request);
        assertEquals(200, result.getStatusCodeValue());
        assertTrue(result.getBody() instanceof SearchResponse);
        assertEquals(1, ((SearchResponse) result.getBody()).getResults().size());
    }

    /**
     * Test search with empty origin (edge case).
     */
    @Test
    @DisplayName("searchFlights: should handle empty origin")
    void testSearchFlights_EmptyOrigin() {
        SearchRequest request = new SearchRequest();
        request.setOrigin("");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1).toString());
        InvalidLocationException ex = new InvalidLocationException("Origin location is required.");
        when(errorHandler.handleError(any(InvalidLocationException.class))).thenReturn(ResponseEntity.badRequest().body("Origin location is required."));

        ResponseEntity<?> result = searchController.searchFlights(request);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Origin location is required.", result.getBody());
    }

    /**
     * Test search with empty destination (edge case).
     */
    @Test
    @DisplayName("searchFlights: should handle empty destination")
    void testSearchFlights_EmptyDestination() {
        SearchRequest request = new SearchRequest();
        request.setOrigin("JFK");
        request.setDestination("");
        request.setDate(LocalDate.now().plusDays(1).toString());
        InvalidLocationException ex = new InvalidLocationException("Destination location is required.");
        when(errorHandler.handleError(any(InvalidLocationException.class))).thenReturn(ResponseEntity.badRequest().body("Destination location is required."));

        ResponseEntity<?> result = searchController.searchFlights(request);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Destination location is required.", result.getBody());
    }

    /**
     * Test search with invalid origin location.
     */
    @Test
    @DisplayName("searchFlights: should handle invalid origin location")
    void testSearchFlights_InvalidOrigin() {
        SearchRequest request = new SearchRequest();
        request.setOrigin("XXX");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1).toString());
        when(locationService.validateLocation("XXX")).thenReturn(false);
        InvalidLocationException ex = new InvalidLocationException("Invalid origin location.");
        when(errorHandler.handleError(any(InvalidLocationException.class))).thenReturn(ResponseEntity.badRequest().body("Invalid origin location."));

        ResponseEntity<?> result = searchController.searchFlights(request);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Invalid origin location.", result.getBody());
    }

    /**
     * Test search with invalid destination location.
     */
    @Test
    @DisplayName("searchFlights: should handle invalid destination location")
    void testSearchFlights_InvalidDestination() {
        SearchRequest request = new SearchRequest();
        request.setOrigin("JFK");
        request.setDestination("YYY");
        request.setDate(LocalDate.now().plusDays(1).toString());
        when(locationService.validateLocation("JFK")).thenReturn(true);
        when(locationService.validateLocation("YYY")).thenReturn(false);
        InvalidLocationException ex = new InvalidLocationException("Invalid destination location.");
        when(errorHandler.handleError(any(InvalidLocationException.class))).thenReturn(ResponseEntity.badRequest().body("Invalid destination location."));

        ResponseEntity<?> result = searchController.searchFlights(request);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Invalid destination location.", result.getBody());
    }

    /**
     * Test search with invalid date format (edge case).
     */
    @Test
    @DisplayName("searchFlights: should handle invalid date format")
    void testSearchFlights_InvalidDateFormat() {
        SearchRequest request = new SearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate("2025/12/01");
        ValidationException ex = new ValidationException("Invalid date format. Use YYYY-MM-DD.");
        when(locationService.validateLocation("JFK")).thenReturn(true);
        when(locationService.validateLocation("LAX")).thenReturn(true);
        when(errorHandler.handleError(any(ValidationException.class))).thenReturn(ResponseEntity.badRequest().body("Invalid date format. Use YYYY-MM-DD."));

        ResponseEntity<?> result = searchController.searchFlights(request);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Invalid date format. Use YYYY-MM-DD.", result.getBody());
    }

    /**
     * Test search with past date (boundary condition).
     */
    @Test
    @DisplayName("searchFlights: should handle past date")
    void testSearchFlights_PastDate() {
        SearchRequest request = new SearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().minusDays(1).toString());
        when(locationService.validateLocation("JFK")).thenReturn(true);
        when(locationService.validateLocation("LAX")).thenReturn(true);
        PastDateException ex = new PastDateException("Travel date cannot be in the past.");
        when(errorHandler.handleError(any(PastDateException.class))).thenReturn(ResponseEntity.badRequest().body("Travel date cannot be in the past."));

        ResponseEntity<?> result = searchController.searchFlights(request);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Travel date cannot be in the past.", result.getBody());
    }

    /**
     * Test search with provider API exception (error scenario).
     */
    @Test
    @DisplayName("searchFlights: should handle ProviderAPIException")
    void testSearchFlights_ProviderAPIException() {
        SearchRequest request = new SearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1).toString());
        when(locationService.validateLocation("JFK")).thenReturn(true);
        when(locationService.validateLocation("LAX")).thenReturn(true);
        when(flightProviderService.queryProviders(any(SearchRequest.class))).thenThrow(new ProviderAPIException("Provider API down"));
        when(errorHandler.handleError(any(ProviderAPIException.class))).thenReturn(ResponseEntity.status(502).body("Provider API down"));

        ResponseEntity<?> result = searchController.searchFlights(request);
        assertEquals(502, result.getStatusCodeValue());
        assertEquals("Provider API down", result.getBody());
    }

    /**
     * Test search with unexpected exception (error-handling scenario).
     */
    @Test
    @DisplayName("searchFlights: should handle unexpected Exception as ValidationException")
    void testSearchFlights_UnexpectedException() {
        SearchRequest request = new SearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1).toString());
        when(locationService.validateLocation("JFK")).thenReturn(true);
        when(locationService.validateLocation("LAX")).thenReturn(true);
        when(flightProviderService.queryProviders(any(SearchRequest.class))).thenThrow(new RuntimeException("DB down"));
        when(errorHandler.handleError(any(ValidationException.class))).thenReturn(ResponseEntity.status(400).body("Internal server error."));

        ResponseEntity<?> result = searchController.searchFlights(request);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Internal server error.", result.getBody());
    }
}
