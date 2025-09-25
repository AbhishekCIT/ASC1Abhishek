package com.example.scheduler.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Report entity (getters/setters and basic object behavior).
 */
class ReportTest {
    @Test
    @DisplayName("Should set and get all fields correctly")
    void testGettersAndSetters() {
        Report report = new Report();
        report.setReportId(20L);
        report.setReportType("Sales");
        report.setFormat("PDF");
        Schedule s1 = new Schedule();
        Schedule s2 = new Schedule();
        report.setSchedules(Arrays.asList(s1, s2));
        assertEquals(20L, report.getReportId());
        assertEquals("Sales", report.getReportType());
        assertEquals("PDF", report.getFormat());
        assertEquals(Arrays.asList(s1, s2), report.getSchedules());
    }

    @Test
    @DisplayName("Should handle null and empty schedules list")
    void testSchedulesNullOrEmpty() {
        Report report = new Report();
        report.setSchedules(null);
        assertNull(report.getSchedules());
        report.setSchedules(Collections.emptyList());
        assertTrue(report.getSchedules().isEmpty());
    }
}
