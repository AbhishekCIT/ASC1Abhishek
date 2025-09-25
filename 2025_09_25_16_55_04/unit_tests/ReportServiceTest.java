package com.example.scheduling.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ReportService.
 */
class ReportServiceTest {
    private final ReportService reportService = new ReportService();

    /**
     * Test supported report type 'PDF'.
     */
    @Test
    @DisplayName("isSupportedReportType: should return true for 'PDF'")
    void testIsSupportedReportType_PDF() {
        assertTrue(reportService.isSupportedReportType("PDF"));
    }

    /**
     * Test supported report type 'Excel'.
     */
    @Test
    @DisplayName("isSupportedReportType: should return true for 'Excel'")
    void testIsSupportedReportType_Excel() {
        assertTrue(reportService.isSupportedReportType("Excel"));
    }

    /**
     * Test supported report type with different case.
     */
    @Test
    @DisplayName("isSupportedReportType: should return true for 'pdf' and 'excel' (case-insensitive)")
    void testIsSupportedReportType_CaseInsensitive() {
        assertTrue(reportService.isSupportedReportType("pdf"));
        assertTrue(reportService.isSupportedReportType("excel"));
    }

    /**
     * Test unsupported report type.
     */
    @Test
    @DisplayName("isSupportedReportType: should return false for unsupported type")
    void testIsSupportedReportType_Unsupported() {
        assertFalse(reportService.isSupportedReportType("CSV"));
        assertFalse(reportService.isSupportedReportType("DOCX"));
        assertFalse(reportService.isSupportedReportType(""));
        assertFalse(reportService.isSupportedReportType(null));
    }
}
