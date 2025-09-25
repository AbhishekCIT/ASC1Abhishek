package com.example.scheduler.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ScheduleListResponse DTO.
 * Verifies all getters and setters, including edge cases.
 */
class ScheduleListResponseTest {
    private ScheduleListResponse response;

    @BeforeEach
    void setUp() {
        response = new ScheduleListResponse();
    }

    @Test
    @DisplayName("Test setting and getting scheduleId")
    void testScheduleId() {
        response.setScheduleId(101L);
        assertEquals(101L, response.getScheduleId());
    }

    @Test
    @DisplayName("Test setting and getting reportId")
    void testReportId() {
        response.setReportId(202L);
        assertEquals(202L, response.getReportId());
    }

    @Test
    @DisplayName("Test setting and getting frequency")
    void testFrequency() {
        response.setFrequency("WEEKLY");
        assertEquals("WEEKLY", response.getFrequency());
    }

    @Test
    @DisplayName("Test setting and getting recipients")
    void testRecipients() {
        List<String> recipients = Arrays.asList("a@example.com", "b@example.com");
        response.setRecipients(recipients);
        assertEquals(recipients, response.getRecipients());
    }

    @Test
    @DisplayName("Test setting and getting deliveryMethod")
    void testDeliveryMethod() {
        response.setDeliveryMethod("EMAIL");
        assertEquals("EMAIL", response.getDeliveryMethod());
    }

    @Test
    @DisplayName("Test setting and getting nextRun")
    void testNextRun() {
        LocalDateTime next = LocalDateTime.now();
        response.setNextRun(next);
        assertEquals(next, response.getNextRun());
    }

    @Test
    @DisplayName("Test setting null values")
    void testNullValues() {
        response.setScheduleId(null);
        response.setReportId(null);
        response.setFrequency(null);
        response.setRecipients(null);
        response.setDeliveryMethod(null);
        response.setNextRun(null);
        assertNull(response.getScheduleId());
        assertNull(response.getReportId());
        assertNull(response.getFrequency());
        assertNull(response.getRecipients());
        assertNull(response.getDeliveryMethod());
        assertNull(response.getNextRun());
    }

    @Test
    @DisplayName("Test setting empty recipients list")
    void testEmptyRecipients() {
        response.setRecipients(Collections.emptyList());
        assertNotNull(response.getRecipients());
        assertTrue(response.getRecipients().isEmpty());
    }
}
