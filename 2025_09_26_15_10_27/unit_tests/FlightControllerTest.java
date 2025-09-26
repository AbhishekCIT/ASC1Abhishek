package com.example.airtransport.controller;

import com.example.airtransport.model.Flight;
import com.example.airtransport.service.FlightInventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightController.
 */
class FlightControllerTest {

    @Mock
    private FlightInventoryService flightInventoryService;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test valid search returns flights and 200 OK")
    void testSearchFlights_Success() {
        // Arrange
        List<Flight> flights = Arrays.asList(
                new Flight("F123", "JFK", "LAX", LocalDate.now().atTime(10, 0), 350.0, 5)
        );
        when(flightInventoryService.searchFlights(eq("JFK"), eq("LAX"), any(LocalDate.class))).thenReturn(flights);

        // Act
        ResponseEntity<?> result = flightController.searchFlights("JFK", "LAX", LocalDate.now().plusDays(1).toString());

        // Assert
        assertEquals(200, result.getStatusCodeValue());
        assertTrue(result.getBody() instanceof List);
        assertEquals(1, ((List<?>) result.getBody()).size());
    }

    @Test
    @DisplayName("Test invalid origin code returns 400 Bad Request")
    void testSearchFlights_InvalidOrigin() {
        ResponseEntity<?> result = flightController.searchFlights("", "LAX", LocalDate.now().plusDays(1).toString());
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Invalid origin airport code", result.getBody());

        result = flightController.searchFlights(null, "LAX", LocalDate.now().plusDays(1).toString());
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Invalid origin airport code", result.getBody());

        result = flightController.searchFlights("JFKK", "LAX", LocalDate.now().plusDays(1).toString());
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Invalid origin airport code", result.getBody());
    }

    @Test
    @DisplayName("Test invalid destination code returns 400 Bad Request")
    void testSearchFlights_InvalidDestination() {
        ResponseEntity<?> result = flightController.searchFlights("JFK", "", LocalDate.now().plusDays(1).toString());
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Invalid destination airport code", result.getBody());

        result = flightController.searchFlights("JFK", null, LocalDate.now().plusDays(1).toString());
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Invalid destination airport code", result.getBody());

        result = flightController.searchFlights("JFK", "LAXX", LocalDate.now().plusDays(1).toString());
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Invalid destination airport code", result.getBody());
    }

    @Test
    @DisplayName("Test invalid date format returns 400 Bad Request")
    void testSearchFlights_InvalidDateFormat() {
        ResponseEntity<?> result = flightController.searchFlights("JFK", "LAX", "2025/10/01");
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Invalid date format. Use yyyy-MM-dd", result.getBody());
    }

    @Test
    @DisplayName("Test travel date in the past returns 400 Bad Request")
    void testSearchFlights_PastDate() {
        ResponseEntity<?> result = flightController.searchFlights("JFK", "LAX", LocalDate.now().minusDays(1).toString());
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Travel date cannot be in the past", result.getBody());
    }

    @Test
    @DisplayName("Test search returns empty list when no flights available")
    void testSearchFlights_NoFlights() {
        when(flightInventoryService.searchFlights(eq("JFK"), eq("LAX"), any(LocalDate.class))).thenReturn(Collections.emptyList());
        ResponseEntity<?> result = flightController.searchFlights("JFK", "LAX", LocalDate.now().plusDays(1).toString());
        assertEquals(200, result.getStatusCodeValue());
        assertTrue(result.getBody() instanceof List);
        assertTrue(((List<?>) result.getBody()).isEmpty());
    }
}
