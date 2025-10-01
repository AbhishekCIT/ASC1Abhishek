package com.example.flightsearch.logger;

import com.example.flightsearch.entity.SearchQuery;
import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.repository.SearchQueryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SearchQueryLogger.
 */
class SearchQueryLoggerTest {

    @Mock
    private SearchQueryRepository searchQueryRepository;

    @InjectMocks
    private SearchQueryLogger logger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal scenario: log() saves SearchQuery with correct fields.
     */
    @Test
    @DisplayName("log() saves SearchQuery with correct fields")
    void testLog_NormalScenario() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setUserId(42L);
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDepartureDate("2025-12-01");
        request.setReturnDate("2025-12-10");
        Map<String, Object> filters = new HashMap<>();
        filters.put("airline", "Delta");
        request.setFilters(filters);

        logger.log(request);

        ArgumentCaptor<SearchQuery> captor = ArgumentCaptor.forClass(SearchQuery.class);
        verify(searchQueryRepository, times(1)).save(captor.capture());
        SearchQuery saved = captor.getValue();
        assertEquals(42L, saved.getUserId());
        assertEquals("JFK", saved.getOrigin());
        assertEquals("LAX", saved.getDestination());
        assertEquals("2025-12-01", saved.getDepartureDate());
        assertEquals("2025-12-10", saved.getReturnDate());
        assertTrue(saved.getFilters().contains("Delta"));
        assertNotNull(saved.getSearchTime());
    }

    /**
     * Test edge case: log() with null filters.
     */
    @Test
    @DisplayName("log() handles null filters")
    void testLog_NullFilters() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setUserId(1L);
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDepartureDate("2025-12-01");
        request.setReturnDate("2025-12-10");
        request.setFilters(null);

        logger.log(request);

        ArgumentCaptor<SearchQuery> captor = ArgumentCaptor.forClass(SearchQuery.class);
        verify(searchQueryRepository, times(1)).save(captor.capture());
        SearchQuery saved = captor.getValue();
        assertNull(saved.getFilters());
    }
}
