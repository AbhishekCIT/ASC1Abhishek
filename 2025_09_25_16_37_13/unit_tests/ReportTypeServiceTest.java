package com.example.scheduling.service;

import com.example.scheduling.model.ReportType;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ReportTypeService stub.
 */
public class ReportTypeServiceTest {

    private final ReportTypeService reportTypeService = new ReportTypeService();

    /**
     * Test getReportTypeById returns empty for any input (stub behavior).
     */
    @Test
    void testGetReportTypeById_AlwaysEmpty() {
        Optional<ReportType> result = reportTypeService.getReportTypeById(1L);
        assertTrue(result.isEmpty(), "Stub should always return empty Optional");
    }

    /**
     * Test getReportTypeById with null input (edge case).
     */
    @Test
    void testGetReportTypeById_NullInput() {
        Optional<ReportType> result = reportTypeService.getReportTypeById(null);
        assertTrue(result.isEmpty(), "Stub should return empty Optional for null input");
    }
}
