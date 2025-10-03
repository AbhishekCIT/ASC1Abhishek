package com.example.airtransport.external;

import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.model.FlightSearchResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for FlightAPIClient.
 * Covers normal, edge, and error-handling scenarios for fetchFlights.
 */
public class FlightAPIClientTest {
    private final FlightAPIClient client = new FlightAPIClient();

    /**
     * Test normal scenario: valid request returns mock response.
     */
    @Test
    void testFetchFlights_Normal() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LHR");
        request.setDepartureDate("2099-12-31");
        FlightSearchResponse response = client.fetchFlights(request);
        assertNotNull(response);
        assertNotNull(response.getFlights());
        assertTrue(response.getTotalResults() > 0);
    }

    /**
     * Test edge case: null request (should not throw, but may return empty or default response).
     */
    @Test
    void testFetchFlights_NullRequest() {
        assertDoesNotThrow(() -> {
            FlightSearchResponse response = client.fetchFlights(null);
            assertNotNull(response);
        });
    }
}
