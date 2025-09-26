package com.example.flightsearch.repository;

import com.example.flightsearch.entity.FlightResult;
import com.example.flightsearch.entity.SearchCriteria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for FlightResultRepository.
 */
@DataJpaTest
public class FlightResultRepositoryTest {
    @Autowired
    private FlightResultRepository flightResultRepository;
    @Autowired
    private SearchCriteriaRepository searchCriteriaRepository;

    @Test
    @DisplayName("Should save and retrieve FlightResult by searchCriteriaId")
    // Tests saving and retrieving FlightResult by searchCriteriaId
    void testFindBySearchCriteriaId() {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setUserId("user1");
        criteria.setOrigin("JFK");
        criteria.setDestination("LAX");
        criteria.setDepartureDate(LocalDate.of(2024, 7, 1));
        criteria.setReturnDate(LocalDate.of(2024, 7, 10));
        criteria.setFilters("{}");
        criteria.setSearchTimestamp(LocalDateTime.now());
        SearchCriteria savedCriteria = searchCriteriaRepository.save(criteria);

        FlightResult flightResult = new FlightResult();
        flightResult.setSearchCriteria(savedCriteria);
        flightResult.setFlightId("DL123");
        flightResult.setAirline("Delta");
        flightResult.setDeparture(LocalDateTime.of(2024, 7, 1, 8, 0));
        flightResult.setArrival(LocalDateTime.of(2024, 7, 1, 11, 0));
        flightResult.setPrice(350.0);
        flightResult.setDuration("3h");
        flightResult.setStops(0);
        flightResultRepository.save(flightResult);

        List<FlightResult> results = flightResultRepository.findBySearchCriteriaId(savedCriteria.getId());
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("DL123", results.get(0).getFlightId());
    }

    @Test
    @DisplayName("Should return empty list for non-existent searchCriteriaId")
    // Tests edge case where no FlightResult exists for given searchCriteriaId
    void testFindBySearchCriteriaId_Empty() {
        List<FlightResult> results = flightResultRepository.findBySearchCriteriaId(-1L);
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
}
