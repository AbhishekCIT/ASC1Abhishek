package com.airtransport.client;

import com.airtransport.model.Flight;
import com.airtransport.model.FlightSearchFilters;
import com.airtransport.model.FlightSearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightAPIClient.
 */
public class FlightAPIClientTest {
    private FlightAPIClient client;

    @BeforeEach
    void setUp() {
        client = new FlightAPIClient();
    }

    /**
     * Test normal scenario: fetchFlights returns a non-empty list for valid input.
     */
    @Test
    void testFetchFlights_NormalScenario() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDepartureDate("2025-12-01");
        List<Flight> flights = client.fetchFlights(request);
        assertNotNull(flights, "Flights list should not be null");
        assertFalse(flights.isEmpty(), "Flights list should not be empty");
        assertEquals("JFK", flights.get(0).getOrigin());
        assertEquals("LAX", flights.get(0).getDestination());
    }

    /**
     * Test edge case: fetchFlights with null request should not throw and should return a list (mock behavior).
     */
    @Test
    void testFetchFlights_NullRequest() {
        List<Flight> flights = client.fetchFlights(null);
        assertNotNull(flights, "Flights list should not be null even for null request");
        assertFalse(flights.isEmpty(), "Flights list should not be empty even for null request");
    }

    /**
     * Test edge case: fetchFlights with missing fields in request.
     */
    @Test
    void testFetchFlights_MissingFields() {
        FlightSearchRequest request = new FlightSearchRequest();
        // No fields set
        List<Flight> flights = client.fetchFlights(request);
        assertNotNull(flights);
        assertFalse(flights.isEmpty());
    }
}
