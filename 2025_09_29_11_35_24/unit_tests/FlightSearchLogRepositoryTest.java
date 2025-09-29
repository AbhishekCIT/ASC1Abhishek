package com.airtransport.repository;

import com.airtransport.model.Flight;
import com.airtransport.model.FlightSearchRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightSearchLogRepository.
 * Since the method is a stub, we test for exceptions and basic invocation.
 */
public class FlightSearchLogRepositoryTest {
    private FlightSearchLogRepository repository;

    @BeforeEach
    void setUp() {
        repository = new FlightSearchLogRepository();
    }

    @AfterEach
    void tearDown() {
        repository = null;
    }

    @Test
    @DisplayName("saveSearchResult does not throw for normal input")
    void testSaveSearchResult_NormalInput() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setUserId("user1");
        request.setSource("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.of(2025, 12, 1));
        request.setPassengerCount(2);
        List<Flight> flights = Arrays.asList(
                new Flight("Delta", "DL123", "10:00", "13:00", "3h", 450),
                new Flight("United", "UA456", "11:00", "14:30", "3h 30m", 470)
        );
        assertDoesNotThrow(() -> repository.saveSearchResult(request, flights));
    }

    @Test
    @DisplayName("saveSearchResult handles empty flights list (edge case)")
    void testSaveSearchResult_EmptyFlights() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setUserId("user2");
        request.setSource("ORD");
        request.setDestination("SFO");
        request.setDate(LocalDate.of(2025, 12, 2));
        request.setPassengerCount(1);
        List<Flight> flights = Collections.emptyList();
        assertDoesNotThrow(() -> repository.saveSearchResult(request, flights));
    }

    @Test
    @DisplayName("saveSearchResult handles null flights list (error scenario)")
    void testSaveSearchResult_NullFlights() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setUserId("user3");
        request.setSource("BOS");
        request.setDestination("MIA");
        request.setDate(LocalDate.of(2025, 12, 3));
        request.setPassengerCount(3);
        assertThrows(NullPointerException.class, () -> repository.saveSearchResult(request, null));
    }

    @Test
    @DisplayName("saveSearchResult handles null request (error scenario)")
    void testSaveSearchResult_NullRequest() {
        List<Flight> flights = Arrays.asList(
                new Flight("Delta", "DL123", "10:00", "13:00", "3h", 450)
        );
        assertThrows(NullPointerException.class, () -> repository.saveSearchResult(null, flights));
    }
}
