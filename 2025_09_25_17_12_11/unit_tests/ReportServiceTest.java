package com.example.scheduler.service;

import com.example.scheduler.entity.Report;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ReportService.
 */
class ReportServiceTest {
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        reportService = new ReportService();
    }

    @Test
    @DisplayName("Generate report - returns non-null byte array")
    void testGenerateReportReturnsByteArray() {
        Report report = new Report();
        report.setId(1L);
        byte[] result = reportService.generateReport(report);
        assertNotNull(result);
        assertEquals(0, result.length); // As per stub implementation
    }

    @Test
    @DisplayName("Validate report - valid id")
    void testValidateReportValidId() {
        assertTrue(reportService.validateReport(1L));
        assertTrue(reportService.validateReport(100L));
    }

    @Test
    @DisplayName("Validate report - invalid id (null or <=0)")
    void testValidateReportInvalidId() {
        assertFalse(reportService.validateReport(null));
        assertFalse(reportService.validateReport(0L));
        assertFalse(reportService.validateReport(-1L));
    }
}
