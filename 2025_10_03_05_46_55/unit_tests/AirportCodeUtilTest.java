package com.asc.airbooking.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for AirportCodeUtil.
 * Covers normal, edge, and error scenarios for isValidAirportCode.
 */
class AirportCodeUtilTest {

    private AirportCodeUtil airportCodeUtil;

    @BeforeEach
    void setUp() {
        airportCodeUtil = new AirportCodeUtil();
    }

    /**
     * Purpose: Test valid airport codes.
     */
    @Test
    void testValidAirportCodes() {
        assertTrue(airportCodeUtil.isValidAirportCode("JFK"));
        assertTrue(airportCodeUtil.isValidAirportCode("LAX"));
        assertTrue(airportCodeUtil.isValidAirportCode("SFO"));
        assertTrue(airportCodeUtil.isValidAirportCode("ORD"));
        assertTrue(airportCodeUtil.isValidAirportCode("ATL"));
    }

    /**
     * Purpose: Test invalid airport code.
     */
    @Test
    void testInvalidAirportCode() {
        assertFalse(airportCodeUtil.isValidAirportCode("XXX"));
        assertFalse(airportCodeUtil.isValidAirportCode(""));
    }

    /**
     * Purpose: Test edge case with null airport code.
     */
    @Test
    void testNullAirportCode() {
        assertFalse(airportCodeUtil.isValidAirportCode(null));
    }
}
