package com.example.scheduling.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SchedulingRule entity.
 */
public class SchedulingRuleTest {

    /**
     * Test all getters and setters for normal scenario.
     */
    @Test
    void testGettersAndSetters_Normal() {
        SchedulingRule rule = new SchedulingRule();
        rule.setId(1L);
        ReportType reportType = new ReportType();
        reportType.setId(2L);
        rule.setReportType(reportType);
        rule.setFrequency("daily");
        rule.setTime("14:00");
        rule.setActive(true);
        LocalDateTime now = LocalDateTime.now();
        rule.setCreatedAt(now);
        rule.setUpdatedAt(now);

        assertEquals(1L, rule.getId());
        assertEquals(reportType, rule.getReportType());
        assertEquals("daily", rule.getFrequency());
        assertEquals("14:00", rule.getTime());
        assertTrue(rule.isActive());
        assertEquals(now, rule.getCreatedAt());
        assertEquals(now, rule.getUpdatedAt());
    }

    /**
     * Test setting and getting null values (edge case).
     */
    @Test
    void testSettersAndGetters_NullValues() {
        SchedulingRule rule = new SchedulingRule();
        rule.setReportType(null);
        rule.setFrequency(null);
        rule.setTime(null);
        rule.setCreatedAt(null);
        rule.setUpdatedAt(null);

        assertNull(rule.getReportType());
        assertNull(rule.getFrequency());
        assertNull(rule.getTime());
        assertNull(rule.getCreatedAt());
        assertNull(rule.getUpdatedAt());
    }

    /**
     * Test boundary condition for isActive field.
     */
    @Test
    void testIsActiveBoundary() {
        SchedulingRule rule = new SchedulingRule();
        rule.setActive(false);
        assertFalse(rule.isActive());
        rule.setActive(true);
        assertTrue(rule.isActive());
    }
}
