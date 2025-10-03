package com.example.airtransport.external;

import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.model.FlightSearchResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for MockFlightData utility.
 * Covers normal and edge cases for getMockResponse.
 */
public class MockFlightDataTest {
    /**
     * Test normal scenario: valid request returns mock response with flights.
     */
    @Test
    void testGetMockResponse_Normal() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LHR");
        request.setDepartureDate("2099-12-31");
        FlightSearchResponse response = MockFlightData.getMockResponse(request);
        assertNotNull(response);
        assertNotNull(response.getFlights());
        assertEquals(1, response.getTotalResults());
    }

    /**
     * Test edge case: null request (should not throw, but may return a default response).
     */
    @Test
    void testGetMockResponse_NullRequest() {
        assertDoesNotThrow(() -> {
            FlightSearchResponse response = MockFlightData.getMockResponse(null);
            assertNotNull(response);
        });
    }
}
