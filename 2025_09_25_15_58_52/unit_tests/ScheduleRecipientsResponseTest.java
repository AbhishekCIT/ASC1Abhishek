package com.example.scheduler.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ScheduleRecipientsResponse DTO.
 * Verifies all getters and setters, including edge cases.
 */
class ScheduleRecipientsResponseTest {
    private ScheduleRecipientsResponse response;

    @BeforeEach
    void setUp() {
        response = new ScheduleRecipientsResponse();
    }

    @Test
    @DisplayName("Test setting and getting scheduleId")
    void testScheduleId() {
        response.setScheduleId(789L);
        assertEquals(789L, response.getScheduleId());
    }

    @Test
    @DisplayName("Test setting and getting recipients")
    void testRecipients() {
        List<String> recipients = Arrays.asList("a@example.com", "b@example.com");
        response.setRecipients(recipients);
        assertEquals(recipients, response.getRecipients());
    }

    @Test
    @DisplayName("Test setting and getting status")
    void testStatus() {
        response.setStatus("UPDATED");
        assertEquals("UPDATED", response.getStatus());
    }

    @Test
    @DisplayName("Test setting null values")
    void testNullValues() {
        response.setScheduleId(null);
        response.setRecipients(null);
        response.setStatus(null);
        assertNull(response.getScheduleId());
        assertNull(response.getRecipients());
        assertNull(response.getStatus());
    }

    @Test
    @DisplayName("Test setting empty recipients list")
    void testEmptyRecipients() {
        response.setRecipients(Collections.emptyList());
        assertNotNull(response.getRecipients());
        assertTrue(response.getRecipients().isEmpty());
    }

    @Test
    @DisplayName("Test setting empty status string")
    void testEmptyStatus() {
        response.setStatus("");
        assertEquals("", response.getStatus());
    }
}
