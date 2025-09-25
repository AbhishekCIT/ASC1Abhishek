package com.example.scheduling.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TimeFormatUtil.
 */
class TimeFormatUtilTest {
    /**
     * Test valid 24-hour time formats.
     */
    @Test
    @DisplayName("isValid24HourFormat: should return true for valid times")
    void testValidTimes() {
        assertTrue(TimeFormatUtil.isValid24HourFormat("00:00"));
        assertTrue(TimeFormatUtil.isValid24HourFormat("09:15"));
        assertTrue(TimeFormatUtil.isValid24HourFormat("12:34"));
        assertTrue(TimeFormatUtil.isValid24HourFormat("23:59"));
        assertTrue(TimeFormatUtil.isValid24HourFormat("19:00"));
    }

    /**
     * Test invalid 24-hour time formats.
     */
    @Test
    @DisplayName("isValid24HourFormat: should return false for invalid times")
    void testInvalidTimes() {
        assertFalse(TimeFormatUtil.isValid24HourFormat("24:00"));
        assertFalse(TimeFormatUtil.isValid24HourFormat("12:60"));
        assertFalse(TimeFormatUtil.isValid24HourFormat("-01:00"));
        assertFalse(TimeFormatUtil.isValid24HourFormat("13:5"));
        assertFalse(TimeFormatUtil.isValid24HourFormat("3:15"));
        assertFalse(TimeFormatUtil.isValid24HourFormat("99:99"));
        assertFalse(TimeFormatUtil.isValid24HourFormat("12:345"));
        assertFalse(TimeFormatUtil.isValid24HourFormat("12-34"));
        assertFalse(TimeFormatUtil.isValid24HourFormat("abcd"));
    }

    /**
     * Test edge cases: null and empty string.
     */
    @Test
    @DisplayName("isValid24HourFormat: should return false for null and empty string")
    void testNullAndEmpty() {
        assertFalse(TimeFormatUtil.isValid24HourFormat(null));
        assertFalse(TimeFormatUtil.isValid24HourFormat(""));
    }
}
