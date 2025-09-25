package com.example.scheduling.service;

import org.springframework.stereotype.Service;

/**
 * Service class for report generation logic.
 * Stub implementation for integration.
 */
@Service
public class ReportService {
    public boolean isSupportedReportType(String reportType) {
        // Assume PDF and Excel are supported
        return "PDF".equalsIgnoreCase(reportType) || "Excel".equalsIgnoreCase(reportType);
    }
    // Add report generation logic as needed
}
