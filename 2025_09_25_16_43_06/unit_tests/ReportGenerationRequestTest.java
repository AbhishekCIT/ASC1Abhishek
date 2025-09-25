package com.example.scheduler.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ReportGenerationRequest (getter/setter and edge cases).
 */
class ReportGenerationRequestTest {
    @Test
    @DisplayName("Should set and get scheduleId correctly")
    void testGettersAndSetters() {
        ReportGenerationRequest req = new ReportGenerationRequest();
        req.setScheduleId(1L);
        assertEquals(1L, req.getScheduleId());
    }

    @Test
    @DisplayName("Should handle null scheduleId")
    void testNullScheduleId() {
        ReportGenerationRequest req = new ReportGenerationRequest();
        req.setScheduleId(null);
        assertNull(req.getScheduleId());
    }
}
