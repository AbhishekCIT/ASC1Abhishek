package com.example.scheduler.service;

import com.example.scheduler.model.ReportGenerationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ReportService.
 */
class ReportServiceTest {
    private final ReportService reportService = new ReportService();

    @Test
    @DisplayName("Should generate report and return response with status 'Generated'")
    void testGenerateReport_Success() {
        ReportGenerationRequest request = new ReportGenerationRequest();
        request.setScheduleId(1L);
        ReportService.ReportGenerationResponse response = reportService.generateReport(request);
        assertNotNull(response);
        assertEquals("/reports/1.pdf", response.getReportUrl());
        assertEquals("Generated", response.getStatus());
    }

    @Test
    @DisplayName("Should handle null request gracefully (edge case)")
    void testGenerateReport_NullRequest() {
        assertThrows(NullPointerException.class, () -> reportService.generateReport(null));
    }
}
