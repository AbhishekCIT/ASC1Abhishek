package com.example.scheduler.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TimeUtil.
 */
public class TimeUtilTest {

    /**
     * Test valid time strings.
     */
    @Test
    void testIsValidTime_ValidTimes() {
        assertTrue(TimeUtil.isValidTime("00:00"));
        assertTrue(TimeUtil.isValidTime("23:59"));
        assertTrue(TimeUtil.isValidTime("08:30"));
        assertTrue(TimeUtil.isValidTime("12:00"));
    }

    /**
     * Test invalid time strings.
     */
    @Test
    void testIsValidTime_InvalidTimes() {
        assertFalse(TimeUtil.isValidTime("24:00")); // out of range
        assertFalse(TimeUtil.isValidTime("12:60")); // out of range
        assertFalse(TimeUtil.isValidTime("-01:00")); // negative
        assertFalse(TimeUtil.isValidTime("8:00")); // missing leading zero
        assertFalse(TimeUtil.isValidTime("08:0")); // missing digit
        assertFalse(TimeUtil.isValidTime("0800")); // missing colon
        assertFalse(TimeUtil.isValidTime("08:00:00")); // extra seconds
        assertFalse(TimeUtil.isValidTime("abc")); // not a time
    }

    /**
     * Test null and empty time strings (edge cases).
     */
    @Test
    void testIsValidTime_NullOrEmpty() {
        assertFalse(TimeUtil.isValidTime(null));
        assertFalse(TimeUtil.isValidTime(""));
    }
}
