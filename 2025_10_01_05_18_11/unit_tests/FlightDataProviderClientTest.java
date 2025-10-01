package com.example.flightsearch.client;

import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.FlightSearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightDataProviderClient.
 */
class FlightDataProviderClientTest {

    private FlightDataProviderClient client;

    @BeforeEach
    void setUp() {
        client = new FlightDataProviderClient();
    }

    /**
     * Test normal scenario: fetchFlights returns mock flights for valid request.
     */
    @Test
    @DisplayName("fetchFlights returns mock flights for valid request")
    void testFetchFlights_NormalScenario() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDepartureDate("2025-12-01");
        List<Flight> flights = client.fetchFlights(request);
        assertNotNull(flights, "Flights list should not be null");
        assertFalse(flights.isEmpty(), "Flights list should not be empty");
        assertEquals(2, flights.size(), "Should return 2 mock flights");
    }

    /**
     * Test edge case: fetchFlights with empty request fields.
     */
    @Test
    @DisplayName("fetchFlights handles empty request fields")
    void testFetchFlights_EmptyFields() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("");
        request.setDestination("");
        request.setDepartureDate("2025-12-01");
        List<Flight> flights = client.fetchFlights(request);
        assertNotNull(flights, "Flights list should not be null even for empty fields");
        assertEquals(2, flights.size(), "Should still return mock flights");
    }

    /**
     * Test boundary condition: fetchFlights with null request.
     */
    @Test
    @DisplayName("fetchFlights throws NullPointerException for null request")
    void testFetchFlights_NullRequest() {
        assertThrows(NullPointerException.class, () -> client.fetchFlights(null), "Should throw NullPointerException for null request");
    }

    /**
     * Test error-handling: fetchFlights with invalid date format in request.
     */
    @Test
    @DisplayName("fetchFlights throws exception for invalid date format")
    void testFetchFlights_InvalidDateFormat() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDepartureDate("invalid-date");
        assertThrows(Exception.class, () -> client.fetchFlights(request), "Should throw exception for invalid date format");
    }
}
