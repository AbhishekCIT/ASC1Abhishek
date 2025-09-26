package com.example.flightsearch.service;

import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AirlineApiIntegrationService.
 */
class AirlineApiIntegrationServiceTest {
    private AirlineApiIntegrationService service;

    @BeforeEach
    void setUp() {
        service = new AirlineApiIntegrationService();
    }

    /**
     * Test normal scenario: syncFlights returns empty list (mocked).
     */
    @Test
    void testSyncFlights_ReturnsEmptyList() {
        FlightSearchRequest request = new FlightSearchRequest();
        List<FlightSearchResponse> result = service.syncFlights(request);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Test edge case: null request (should not throw, returns empty list).
     */
    @Test
    void testSyncFlights_NullRequest() {
        List<FlightSearchResponse> result = service.syncFlights(null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
