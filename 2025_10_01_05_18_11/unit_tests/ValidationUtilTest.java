package com.example.flightsearch.util;

import com.example.flightsearch.exception.InvalidInputException;
import com.example.flightsearch.model.FlightSearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ValidationUtil.
 */
class ValidationUtilTest {

    private ValidationUtil util;

    @BeforeEach
    void setUp() {
        util = new ValidationUtil();
    }

    /**
     * Test normal scenario: valid request passes validation.
     */
    @Test
    @DisplayName("validateSearchParams passes for valid request")
    void testValidateSearchParams_Valid() {
        FlightSearchRequest req = new FlightSearchRequest();
        req.setOrigin("JFK");
        req.setDestination("LAX");
        req.setDepartureDate(LocalDate.now().plusDays(1).toString());
        req.setReturnDate(LocalDate.now().plusDays(5).toString());
        assertDoesNotThrow(() -> util.validateSearchParams(req));
    }

    /**
     * Test edge case: empty origin.
     */
    @Test
    @DisplayName("validateSearchParams throws for empty origin")
    void testValidateSearchParams_EmptyOrigin() {
        FlightSearchRequest req = new FlightSearchRequest();
        req.setOrigin("");
        req.setDestination("LAX");
        req.setDepartureDate(LocalDate.now().plusDays(1).toString());
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> util.validateSearchParams(req));
        assertEquals("Origin must not be empty", ex.getMessage());
    }

    /**
     * Test edge case: empty destination.
     */
    @Test
    @DisplayName("validateSearchParams throws for empty destination")
    void testValidateSearchParams_EmptyDestination() {
        FlightSearchRequest req = new FlightSearchRequest();
        req.setOrigin("JFK");
        req.setDestination("");
        req.setDepartureDate(LocalDate.now().plusDays(1).toString());
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> util.validateSearchParams(req));
        assertEquals("Destination must not be empty", ex.getMessage());
    }

    /**
     * Test error scenario: invalid date format.
     */
    @Test
    @DisplayName("validateSearchParams throws for invalid date format")
    void testValidateSearchParams_InvalidDateFormat() {
        FlightSearchRequest req = new FlightSearchRequest();
        req.setOrigin("JFK");
        req.setDestination("LAX");
        req.setDepartureDate("2025/12/01");
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> util.validateSearchParams(req));
        assertEquals("Invalid date format. Use YYYY-MM-DD", ex.getMessage());
    }

    /**
     * Test boundary condition: departure date in the past.
     */
    @Test
    @DisplayName("validateSearchParams throws for past departure date")
    void testValidateSearchParams_DepartureDatePast() {
        FlightSearchRequest req = new FlightSearchRequest();
        req.setOrigin("JFK");
        req.setDestination("LAX");
        req.setDepartureDate(LocalDate.now().minusDays(1).toString());
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> util.validateSearchParams(req));
        assertEquals("Departure date must be in the future", ex.getMessage());
    }

    /**
     * Test boundary condition: return date before departure date.
     */
    @Test
    @DisplayName("validateSearchParams throws for return date before departure date")
    void testValidateSearchParams_ReturnDateBeforeDeparture() {
        FlightSearchRequest req = new FlightSearchRequest();
        req.setOrigin("JFK");
        req.setDestination("LAX");
        req.setDepartureDate(LocalDate.now().plusDays(5).toString());
        req.setReturnDate(LocalDate.now().plusDays(1).toString());
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> util.validateSearchParams(req));
        assertEquals("Return date must be after departure date", ex.getMessage());
    }

    /**
     * Test edge case: null return date (should pass).
     */
    @Test
    @DisplayName("validateSearchParams passes for null return date")
    void testValidateSearchParams_NullReturnDate() {
        FlightSearchRequest req = new FlightSearchRequest();
        req.setOrigin("JFK");
        req.setDestination("LAX");
        req.setDepartureDate(LocalDate.now().plusDays(1).toString());
        req.setReturnDate(null);
        assertDoesNotThrow(() -> util.validateSearchParams(req));
    }
}
