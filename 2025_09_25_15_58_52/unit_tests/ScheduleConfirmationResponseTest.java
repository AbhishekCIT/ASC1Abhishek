package com.example.scheduler.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ScheduleConfirmationResponse DTO.
 * Verifies all getters and setters, including edge cases.
 */
class ScheduleConfirmationResponseTest {
    private ScheduleConfirmationResponse response;

    @BeforeEach
    void setUp() {
        response = new ScheduleConfirmationResponse();
    }

    @Test
    @DisplayName("Test setting and getting scheduleId")
    void testScheduleId() {
        response.setScheduleId(456L);
        assertEquals(456L, response.getScheduleId());
    }

    @Test
    @DisplayName("Test setting and getting status")
    void testStatus() {
        response.setStatus("SCHEDULED");
        assertEquals("SCHEDULED", response.getStatus());
    }

    @Test
    @DisplayName("Test setting and getting confirmation")
    void testConfirmation() {
        response.setConfirmation("Report scheduled successfully.");
        assertEquals("Report scheduled successfully.", response.getConfirmation());
    }

    @Test
    @DisplayName("Test setting null values")
    void testNullValues() {
        response.setScheduleId(null);
        response.setStatus(null);
        response.setConfirmation(null);
        assertNull(response.getScheduleId());
        assertNull(response.getStatus());
        assertNull(response.getConfirmation());
    }

    @Test
    @DisplayName("Test setting empty strings")
    void testEmptyStrings() {
        response.setStatus("");
        response.setConfirmation("");
        assertEquals("", response.getStatus());
        assertEquals("", response.getConfirmation());
    }
}
