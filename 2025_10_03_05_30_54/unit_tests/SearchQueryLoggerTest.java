package com.example.airtransport.util;

import com.example.airtransport.entity.SearchQuery;
import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.model.FlightSearchResponse;
import com.example.airtransport.repository.SearchQueryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * JUnit tests for SearchQueryLogger utility.
 * Covers normal and edge cases for logQuery.
 */
public class SearchQueryLoggerTest {
    @Mock
    private SearchQueryRepository searchQueryRepository;

    @InjectMocks
    private SearchQueryLogger searchQueryLogger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal scenario: logQuery saves SearchQuery entity.
     */
    @Test
    void testLogQuery_Normal() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setUserId("user1");
        request.setOrigin("JFK");
        request.setDestination("LHR");
        request.setDepartureDate("2099-12-31");
        request.setReturnDate("2100-01-10");
        FlightSearchResponse response = new FlightSearchResponse();

        searchQueryLogger.logQuery(request, response);
        verify(searchQueryRepository, times(1)).save(any(SearchQuery.class));
    }

    /**
     * Test edge case: null request and response (should not throw, but may save incomplete entity).
     */
    @Test
    void testLogQuery_NullRequestAndResponse() {
        searchQueryLogger.logQuery(null, null);
        verify(searchQueryRepository, times(1)).save(any(SearchQuery.class));
    }
}
