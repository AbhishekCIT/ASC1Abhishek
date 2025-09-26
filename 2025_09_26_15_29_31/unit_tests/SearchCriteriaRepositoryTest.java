package com.example.flightsearch.repository;

import com.example.flightsearch.entity.SearchCriteria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for SearchCriteriaRepository.
 */
@DataJpaTest
public class SearchCriteriaRepositoryTest {
    @Autowired
    private SearchCriteriaRepository searchCriteriaRepository;

    @Test
    @DisplayName("Should save and retrieve SearchCriteria by id")
    // Tests saving and retrieving SearchCriteria by id
    void testSaveAndFindById() {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setUserId("user1");
        criteria.setOrigin("JFK");
        criteria.setDestination("LAX");
        criteria.setDepartureDate(LocalDate.of(2024, 7, 1));
        criteria.setReturnDate(LocalDate.of(2024, 7, 10));
        criteria.setFilters("{}");
        criteria.setSearchTimestamp(LocalDateTime.now());
        SearchCriteria saved = searchCriteriaRepository.save(criteria);
        Optional<SearchCriteria> found = searchCriteriaRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("user1", found.get().getUserId());
    }

    @Test
    @DisplayName("Should return empty Optional for non-existent id")
    // Tests edge case where no SearchCriteria exists for given id
    void testFindById_Empty() {
        Optional<SearchCriteria> found = searchCriteriaRepository.findById(-1L);
        assertFalse(found.isPresent());
    }
}
