package com.example.flightsearch.repository;

import com.example.flightsearch.entity.SearchQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for SearchQueryRepository.
 */
@DataJpaTest
class SearchQueryRepositoryTest {

    @Autowired
    private SearchQueryRepository repository;

    /**
     * Test normal scenario: save and retrieve SearchQuery.
     */
    @Test
    @DisplayName("Save and retrieve SearchQuery")
    void testSaveAndRetrieve() {
        SearchQuery query = new SearchQuery();
        query.setUserId(1L);
        query.setOrigin("JFK");
        query.setDestination("LAX");
        query.setDepartureDate("2025-12-01");
        query.setReturnDate("2025-12-10");
        query.setFilters("{\"airline\":\"Delta\"}");
        query.setSearchTime(LocalDateTime.now());

        SearchQuery saved = repository.save(query);
        Optional<SearchQuery> found = repository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("JFK", found.get().getOrigin());
    }

    /**
     * Test edge case: findById returns empty for non-existent id.
     */
    @Test
    @DisplayName("findById returns empty for non-existent id")
    void testFindById_NotFound() {
        Optional<SearchQuery> found = repository.findById(-1L);
        assertFalse(found.isPresent());
    }
}
