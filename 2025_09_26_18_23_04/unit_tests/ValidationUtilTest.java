package com.example.flightsearch.util;

import com.example.flightsearch.model.FlightSearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ValidationUtil (validateSearchParams).
 */
class ValidationUtilTest {
    private ValidationUtil validationUtil;
    private FlightSearchRequest request;

    @BeforeEach
    void setUp() {
        validationUtil = new ValidationUtil();
        request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengers(1);
        request.setFlightClass("Economy");
    }

    /**
     * Test normal scenario: valid request passes.
     */
    @Test
    void testValidateSearchParams_Valid() {
        assertDoesNotThrow(() -> validationUtil.validateSearchParams(request));
    }

    /**
     * Test invalid origin (null, empty, invalid code).
     */
    @Test
    void testValidateSearchParams_InvalidOrigin() {
        request.setOrigin(null);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        assertTrue(ex.getMessage().contains("Invalid origin"));
        request.setOrigin("");
        ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        assertTrue(ex.getMessage().contains("Invalid origin"));
        request.setOrigin("XXX");
        ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        assertTrue(ex.getMessage().contains("Invalid origin"));
    }

    /**
     * Test invalid destination (null, empty, invalid code).
     */
    @Test
    void testValidateSearchParams_InvalidDestination() {
        request.setDestination(null);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        assertTrue(ex.getMessage().contains("Invalid destination"));
        request.setDestination("");
        ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        assertTrue(ex.getMessage().contains("Invalid destination"));
        request.setDestination("YYY");
        ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        assertTrue(ex.getMessage().contains("Invalid destination"));
    }

    /**
     * Test origin and destination are the same.
     */
    @Test
    void testValidateSearchParams_OriginEqualsDestination() {
        request.setDestination("JFK");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        assertTrue(ex.getMessage().contains("Origin and destination cannot be the same"));
    }

    /**
     * Test travel date in the past and null.
     */
    @Test
    void testValidateSearchParams_InvalidDate() {
        request.setDate(LocalDate.now().minusDays(1));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        assertTrue(ex.getMessage().contains("Travel date cannot be in the past"));
        request.setDate(null);
        ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        assertTrue(ex.getMessage().contains("Travel date cannot be in the past"));
    }

    /**
     * Test passengers less than 1.
     */
    @Test
    void testValidateSearchParams_InvalidPassengers() {
        request.setPassengers(0);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        assertTrue(ex.getMessage().contains("Passengers must be at least 1"));
        request.setPassengers(-5);
        ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        assertTrue(ex.getMessage().contains("Passengers must be at least 1"));
    }

    /**
     * Test invalid class of service (null, invalid value).
     */
    @Test
    void testValidateSearchParams_InvalidClass() {
        request.setFlightClass(null);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        assertTrue(ex.getMessage().contains("Invalid class of service"));
        request.setFlightClass("Premium");
        ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        assertTrue(ex.getMessage().contains("Invalid class of service"));
    }

    /**
     * Test multiple errors in one request.
     */
    @Test
    void testValidateSearchParams_MultipleErrors() {
        request.setOrigin("XXX");
        request.setDestination("XXX");
        request.setDate(LocalDate.now().minusDays(2));
        request.setPassengers(0);
        request.setFlightClass("Invalid");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> validationUtil.validateSearchParams(request));
        String msg = ex.getMessage();
        assertTrue(msg.contains("Invalid origin"));
        assertTrue(msg.contains("Invalid destination"));
        assertTrue(msg.contains("Origin and destination cannot be the same"));
        assertTrue(msg.contains("Travel date cannot be in the past"));
        assertTrue(msg.contains("Passengers must be at least 1"));
        assertTrue(msg.contains("Invalid class of service"));
    }
}
