package com.example.airtransport.util;

import com.example.airtransport.model.FlightSearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for ValidationUtil.
 * Covers normal, edge, boundary, and error-handling scenarios for validateSearchCriteria.
 */
public class ValidationUtilTest {
    private ValidationUtil validationUtil;

    @BeforeEach
    void setUp() {
        validationUtil = new ValidationUtil();
    }

    /**
     * Test normal scenario: valid search criteria passes validation.
     */
    @Test
    void testValidateSearchCriteria_Valid() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LHR");
        request.setDepartureDate(LocalDate.now().plusDays(1).toString());
        request.setReturnDate(LocalDate.now().plusDays(10).toString());
        assertDoesNotThrow(() -> validationUtil.validateSearchCriteria(request));
    }

    /**
     * Test error: missing mandatory fields.
     */
    @Test
    void testValidateSearchCriteria_MissingFields() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin(null);
        request.setDestination("LHR");
        request.setDepartureDate(LocalDate.now().plusDays(1).toString());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchCriteria(request));
        assertEquals("All mandatory fields must be filled.", ex.getMessage());
    }

    /**
     * Test error: origin and destination are the same.
     */
    @Test
    void testValidateSearchCriteria_SameOriginDestination() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("JFK");
        request.setDepartureDate(LocalDate.now().plusDays(1).toString());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchCriteria(request));
        assertEquals("Origin and destination cannot be the same.", ex.getMessage());
    }

    /**
     * Test error: departure date in the past.
     */
    @Test
    void testValidateSearchCriteria_DepartureInPast() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LHR");
        request.setDepartureDate(LocalDate.now().minusDays(1).toString());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchCriteria(request));
        assertEquals("Travel dates must be in the future.", ex.getMessage());
    }

    /**
     * Test error: return date before departure date.
     */
    @Test
    void testValidateSearchCriteria_ReturnBeforeDeparture() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LHR");
        request.setDepartureDate(LocalDate.now().plusDays(10).toString());
        request.setReturnDate(LocalDate.now().plusDays(5).toString());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchCriteria(request));
        assertEquals("Return date must be after departure date.", ex.getMessage());
    }
}
