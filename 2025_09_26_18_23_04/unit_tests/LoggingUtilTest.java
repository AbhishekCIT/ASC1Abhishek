package com.example.flightsearch.util;

import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Unit tests for LoggingUtil.
 * Note: Actual logging output is not asserted, but method coverage is ensured.
 */
class LoggingUtilTest {
    private LoggingUtil loggingUtil;

    @BeforeEach
    void setUp() {
        loggingUtil = new LoggingUtil();
    }

    /**
     * Test logSearch with normal request and response list.
     */
    @Test
    void testLogSearch_Normal() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        FlightSearchResponse resp = new FlightSearchResponse();
        resp.setFlightId(1L);
        List<FlightSearchResponse> responses = Arrays.asList(resp);
        loggingUtil.logSearch(request, responses);
        // No exception means pass
    }

    /**
     * Test logSearch with empty response list.
     */
    @Test
    void testLogSearch_EmptyResponse() {
        FlightSearchRequest request = new FlightSearchRequest();
        List<FlightSearchResponse> responses = Collections.emptyList();
        loggingUtil.logSearch(request, responses);
    }

    /**
     * Test logSearch with null response list.
     */
    @Test
    void testLogSearch_NullResponse() {
        FlightSearchRequest request = new FlightSearchRequest();
        loggingUtil.logSearch(request, null);
    }

    /**
     * Test logSearch with null request.
     */
    @Test
    void testLogSearch_NullRequest() {
        FlightSearchResponse resp = new FlightSearchResponse();
        List<FlightSearchResponse> responses = Arrays.asList(resp);
        loggingUtil.logSearch(null, responses);
    }
}
