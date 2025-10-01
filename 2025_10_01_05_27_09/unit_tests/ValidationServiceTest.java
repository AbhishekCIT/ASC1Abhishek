package com.example.flightsearch.util;

import com.example.flightsearch.exception.InvalidAirportCodeException;
import com.example.flightsearch.exception.InvalidDateException;
import com.example.flightsearch.exception.InvalidPassengerCountException;
import com.example.flightsearch.model.FlightSearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ValidationService.
 */
class ValidationServiceTest {
    private ValidationService validationService;
    private FlightSearchRequest request;

    @BeforeEach
    void setUp() {
        validationService = new ValidationService();
        request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengers(1);
    }

    /**
     * Test valid parameters pass validation.
     */
    @Test
    @DisplayName("validateSearchParams passes for valid input")
    void testValidateSearchParams_Valid() {
        assertDoesNotThrow(() -> validationService.validateSearchParams(request));
    }

    /**
     * Test invalid origin airport code.
     */
    @Test
    @DisplayName("validateSearchParams throws for invalid origin")
    void testValidateSearchParams_InvalidOrigin() {
        request.setOrigin("XXX");
        assertThrows(InvalidAirportCodeException.class, () -> validationService.validateSearchParams(request));
    }

    /**
     * Test invalid destination airport code.
     */
    @Test
    @DisplayName("validateSearchParams throws for invalid destination")
    void testValidateSearchParams_InvalidDestination() {
        request.setDestination("YYY");
        assertThrows(InvalidAirportCodeException.class, () -> validationService.validateSearchParams(request));
    }

    /**
     * Test travel date in the past.
     */
    @Test
    @DisplayName("validateSearchParams throws for past date")
    void testValidateSearchParams_PastDate() {
        request.setDate(LocalDate.now().minusDays(1));
        assertThrows(InvalidDateException.class, () -> validationService.validateSearchParams(request));
    }

    /**
     * Test null travel date.
     */
    @Test
    @DisplayName("validateSearchParams throws for null date")
    void testValidateSearchParams_NullDate() {
        request.setDate(null);
        assertThrows(InvalidDateException.class, () -> validationService.validateSearchParams(request));
    }

    /**
     * Test passenger count less than 1.
     */
    @Test
    @DisplayName("validateSearchParams throws for passenger count < 1")
    void testValidateSearchParams_PassengerCountTooLow() {
        request.setPassengers(0);
        assertThrows(InvalidPassengerCountException.class, () -> validationService.validateSearchParams(request));
    }

    /**
     * Test passenger count greater than max allowed.
     */
    @Test
    @DisplayName("validateSearchParams throws for passenger count > max")
    void testValidateSearchParams_PassengerCountTooHigh() {
        request.setPassengers(10);
        assertThrows(InvalidPassengerCountException.class, () -> validationService.validateSearchParams(request));
    }
}
