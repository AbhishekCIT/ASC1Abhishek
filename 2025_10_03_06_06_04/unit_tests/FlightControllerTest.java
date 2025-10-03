package com.example.airtransport.controller;

import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.model.FlightSearchResponse;
import com.example.airtransport.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
 * Covers normal, edge, and error scenarios for flight search.
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

    @Nested
    @DisplayName("searchFlights() tests")
    class SearchFlightsTests {
        @Test
        @DisplayName("Should return list of flights for valid search request")
        void testSearchFlightsSuccess() {
            // Arrange
            FlightSearchRequest request = new FlightSearchRequest();
            FlightSearchResponse flight1 = new FlightSearchResponse();
            FlightSearchResponse flight2 = new FlightSearchResponse();
            List<FlightSearchResponse> expected = Arrays.asList(flight1, flight2);
            when(flightService.searchFlights(any())).thenReturn(expected);

            // Act
            ResponseEntity<List<FlightSearchResponse>> response = flightController.searchFlights(request);

            // Assert
            assertNotNull(response);
            assertEquals(200, response.getStatusCodeValue());
            assertEquals(expected, response.getBody());
        }

        @Test
        @DisplayName("Should return empty list if no flights found (edge case)")
        void testSearchFlightsNoResults() {
            // Arrange
            FlightSearchRequest request = new FlightSearchRequest();
            when(flightService.searchFlights(any())).thenReturn(Collections.emptyList());

            // Act
            ResponseEntity<List<FlightSearchResponse>> response = flightController.searchFlights(request);

            // Assert
            assertNotNull(response);
            assertEquals(200, response.getStatusCodeValue());
            assertTrue(response.getBody().isEmpty());
        }

        @Test
        @DisplayName("Should handle null request (error case)")
        void testSearchFlightsNullRequest() {
            // Purpose: Should throw exception if request is null (validation handled by framework)
            assertThrows(NullPointerException.class, () -> flightController.searchFlights(null));
        }

        @Test
        @DisplayName("Should handle service exception (error case)")
        void testSearchFlightsServiceException() {
            // Arrange
            FlightSearchRequest request = new FlightSearchRequest();
            when(flightService.searchFlights(any())).thenThrow(new RuntimeException("Service error"));

            // Act & Assert
            assertThrows(RuntimeException.class, () -> flightController.searchFlights(request));
        }
    }
}
