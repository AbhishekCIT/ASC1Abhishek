package com.example.airtransport.repository;

import com.example.airtransport.entity.SearchQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for SearchQueryRepository.
 * Covers basic CRUD operations and edge cases.
 */
@DataJpaTest
public class SearchQueryRepositoryTest {
    @Autowired
    private SearchQueryRepository repository;

    /**
     * Test saving and retrieving a SearchQuery entity.
     */
    @Test
    void testSaveAndFindById() {
        SearchQuery query = new SearchQuery();
        query.setUserId("user1");
        query.setOrigin("JFK");
        query.setDestination("LHR");
        query.setDepartureDate("2099-12-31");
        query.setReturnDate("2100-01-10");
        query.setTimestamp(LocalDateTime.now());
        SearchQuery saved = repository.save(query);
        Optional<SearchQuery> found = repository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("user1", found.get().getUserId());
    }

    /**
     * Test edge case: findById with non-existent ID returns empty.
     */
    @Test
    void testFindById_NotFound() {
        Optional<SearchQuery> found = repository.findById(-1L);
        assertFalse(found.isPresent());
    }
}
