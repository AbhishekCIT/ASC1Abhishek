package com.airtransport.client;

import com.airtransport.model.Flight;
import com.airtransport.model.FlightSearchRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ExternalFlightApiClient.
 */
public class ExternalFlightApiClientTest {
    private ExternalFlightApiClient apiClient;

    @BeforeEach
    void setUp() {
        apiClient = new ExternalFlightApiClient();
    }

    @AfterEach
    void tearDown() {
        apiClient = null;
    }

    @Test
    @DisplayName("fetchFlights returns flights for JFK to LAX (normal scenario)")
    void testFetchFlights_JFKToLAX_ReturnsFlights() {
        // Arrange
        FlightSearchRequest request = new FlightSearchRequest("JFK", "LAX", "2025-12-01", 1);
        // Act
        List<Flight> flights = apiClient.fetchFlights(request);
        // Assert
        assertNotNull(flights, "Flights list should not be null");
        assertEquals(2, flights.size(), "Should return 2 flights for JFK to LAX");
        assertEquals("Delta", flights.get(0).getAirline());
        assertEquals("United", flights.get(1).getAirline());
    }

    @Test
    @DisplayName("fetchFlights returns empty list for unsupported route (edge case)")
    void testFetchFlights_UnsupportedRoute_ReturnsEmptyList() {
        // Arrange
        FlightSearchRequest request = new FlightSearchRequest("ORD", "SFO", "2025-12-01", 1);
        // Act
        List<Flight> flights = apiClient.fetchFlights(request);
        // Assert
        assertNotNull(flights, "Flights list should not be null");
        assertTrue(flights.isEmpty(), "Should return empty list for unsupported route");
    }

    @Test
    @DisplayName("fetchFlights handles case-insensitive airport codes (boundary condition)")
    void testFetchFlights_CaseInsensitiveAirportCodes() {
        // Arrange
        FlightSearchRequest request = new FlightSearchRequest("jfk", "lax", "2025-12-01", 1);
        // Act
        List<Flight> flights = apiClient.fetchFlights(request);
        // Assert
        assertNotNull(flights);
        assertEquals(2, flights.size(), "Should return 2 flights for lowercase airport codes");
    }

    @Test
    @DisplayName("fetchFlights returns empty list for same source and destination (error scenario)")
    void testFetchFlights_SameSourceAndDestination() {
        // Arrange
        FlightSearchRequest request = new FlightSearchRequest("JFK", "JFK", "2025-12-01", 1);
        // Act
        List<Flight> flights = apiClient.fetchFlights(request);
        // Assert
        assertNotNull(flights);
        assertTrue(flights.isEmpty(), "Should return empty list when source and destination are the same");
    }

    @Test
    @DisplayName("fetchFlights returns empty list for null request (error scenario)")
    void testFetchFlights_NullRequest() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> apiClient.fetchFlights(null), "Should throw NullPointerException for null request");
    }
}
