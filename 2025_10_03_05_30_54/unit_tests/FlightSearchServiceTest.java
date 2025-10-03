package com.example.airtransport.service;

import com.example.airtransport.external.FlightAPIClient;
import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.model.FlightSearchResponse;
import com.example.airtransport.util.SearchQueryLogger;
import com.example.airtransport.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for FlightSearchService.
 * Covers normal, edge, and error-handling scenarios for searchFlights.
 */
public class FlightSearchServiceTest {
    @Mock
    private FlightAPIClient flightAPIClient;
    @Mock
    private ValidationUtil validationUtil;
    @Mock
    private SearchQueryLogger searchQueryLogger;

    @InjectMocks
    private FlightSearchService flightSearchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal scenario: valid request returns response.
     */
    @Test
    void testSearchFlights_Success() {
        FlightSearchRequest request = new FlightSearchRequest();
        FlightSearchResponse response = new FlightSearchResponse();
        doNothing().when(validationUtil).validateSearchCriteria(request);
        when(flightAPIClient.fetchFlights(request)).thenReturn(response);
        doNothing().when(searchQueryLogger).logQuery(request, response);

        FlightSearchResponse result = flightSearchService.searchFlights(request);
        assertSame(response, result);
        verify(validationUtil).validateSearchCriteria(request);
        verify(flightAPIClient).fetchFlights(request);
        verify(searchQueryLogger).logQuery(request, response);
    }

    /**
     * Test error scenario: validation fails and throws exception.
     */
    @Test
    void testSearchFlights_ValidationError() {
        FlightSearchRequest request = new FlightSearchRequest();
        doThrow(new IllegalArgumentException("Invalid input")).when(validationUtil).validateSearchCriteria(request);

        assertThrows(IllegalArgumentException.class, () -> flightSearchService.searchFlights(request));
        verify(validationUtil).validateSearchCriteria(request);
        verifyNoInteractions(flightAPIClient);
        verifyNoInteractions(searchQueryLogger);
    }
}
