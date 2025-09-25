package com.example.scheduledreporting.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for ScheduledReportResponse DTO.
 * Covers builder, constructors, getters, setters, and edge cases.
 */
class ScheduledReportResponseTest {

    @Test
    @DisplayName("Builder creates correct object")
    void testBuilder() {
        ScheduledReportResponse resp = ScheduledReportResponse.builder()
                .id(10)
                .status("SCHEDULED")
                .message("Scheduled successfully")
                .build();
        assertEquals(10, resp.getId());
        assertEquals("SCHEDULED", resp.getStatus());
        assertEquals("Scheduled successfully", resp.getMessage());
    }

    @Test
    @DisplayName("All-args constructor sets fields correctly")
    void testAllArgsConstructor() {
        ScheduledReportResponse resp = new ScheduledReportResponse(5, "UPDATED", "Updated ok");
        assertEquals(5, resp.getId());
        assertEquals("UPDATED", resp.getStatus());
        assertEquals("Updated ok", resp.getMessage());
    }

    @Test
    @DisplayName("No-args constructor and setters")
    void testNoArgsConstructorAndSetters() {
        ScheduledReportResponse resp = new ScheduledReportResponse();
        resp.setId(7);
        resp.setStatus("DELETED");
        resp.setMessage("Deleted ok");
        assertEquals(7, resp.getId());
        assertEquals("DELETED", resp.getStatus());
        assertEquals("Deleted ok", resp.getMessage());
    }

    @Test
    @DisplayName("Null fields are allowed")
    void testNullFields() {
        ScheduledReportResponse resp = new ScheduledReportResponse(null, null, null);
        assertNull(resp.getId());
        assertNull(resp.getStatus());
        assertNull(resp.getMessage());
    }
}
