package com.airtransport.util;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for AirportCodeValidatorUtil.
 * Covers normal, edge, and boundary cases for isValidAirportCode.
 */
class AirportCodeValidatorUtilTest {
    /**
     * Test valid airport codes (normal case).
     */
    @Test
    void testIsValidAirportCode_ValidCodes() {
        assertTrue(AirportCodeValidatorUtil.isValidAirportCode("JFK"));
        assertTrue(AirportCodeValidatorUtil.isValidAirportCode("LAX"));
        assertTrue(AirportCodeValidatorUtil.isValidAirportCode("SFO"));
        assertTrue(AirportCodeValidatorUtil.isValidAirportCode("ORD"));
        assertTrue(AirportCodeValidatorUtil.isValidAirportCode("ATL"));
        assertTrue(AirportCodeValidatorUtil.isValidAirportCode("DFW"));
    }

    /**
     * Test valid airport codes with lowercase input (edge case).
     */
    @Test
    void testIsValidAirportCode_Lowercase() {
        assertTrue(AirportCodeValidatorUtil.isValidAirportCode("jfk"));
        assertTrue(AirportCodeValidatorUtil.isValidAirportCode("lax"));
    }

    /**
     * Test invalid airport code (edge case).
     */
    @Test
    void testIsValidAirportCode_InvalidCode() {
        assertFalse(AirportCodeValidatorUtil.isValidAirportCode("XXX"));
        assertFalse(AirportCodeValidatorUtil.isValidAirportCode(""));
    }

    /**
     * Test null airport code (boundary case).
     */
    @Test
    void testIsValidAirportCode_Null() {
        assertFalse(AirportCodeValidatorUtil.isValidAirportCode(null));
    }
}
