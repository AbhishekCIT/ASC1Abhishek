package com.example.scheduler.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Report entity.
 * Verifies all getters and setters, including edge cases.
 */
class ReportTest {
    private Report report;

    @BeforeEach
    void setUp() {
        report = new Report();
    }

    @Test
    @DisplayName("Test setting and getting id")
    void testId() {
        report.setId(5L);
        assertEquals(5L, report.getId());
    }

    @Test
    @DisplayName("Test setting and getting reportName")
    void testReportName() {
        report.setReportName("Monthly Report");
        assertEquals("Monthly Report", report.getReportName());
    }

    @Test
    @DisplayName("Test setting and getting format")
    void testFormat() {
        report.setFormat("PDF");
        assertEquals("PDF", report.getFormat());
    }

    @Test
    @DisplayName("Test setting null values")
    void testNullValues() {
        report.setId(null);
        report.setReportName(null);
        report.setFormat(null);
        assertNull(report.getId());
        assertNull(report.getReportName());
        assertNull(report.getFormat());
    }

    @Test
    @DisplayName("Test setting empty strings")
    void testEmptyStrings() {
        report.setReportName("");
        report.setFormat("");
        assertEquals("", report.getReportName());
        assertEquals("", report.getFormat());
    }
}
