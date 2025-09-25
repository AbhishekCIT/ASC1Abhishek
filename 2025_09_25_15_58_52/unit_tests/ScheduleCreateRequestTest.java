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
 * Unit tests for ScheduleCreateRequest DTO.
 * Verifies all getters and setters, including edge cases.
 */
class ScheduleCreateRequestTest {
    private ScheduleCreateRequest request;

    @BeforeEach
    void setUp() {
        request = new ScheduleCreateRequest();
    }

    @Test
    @DisplayName("Test setting and getting reportId")
    void testReportId() {
        request.setReportId(123L);
        assertEquals(123L, request.getReportId());
    }

    @Test
    @DisplayName("Test setting and getting frequency")
    void testFrequency() {
        request.setFrequency("DAILY");
        assertEquals("DAILY", request.getFrequency());
    }

    @Test
    @DisplayName("Test setting and getting recipients")
    void testRecipients() {
        List<String> recipients = Arrays.asList("a@example.com", "b@example.com");
        request.setRecipients(recipients);
        assertEquals(recipients, request.getRecipients());
    }

    @Test
    @DisplayName("Test setting and getting deliveryMethod")
    void testDeliveryMethod() {
        request.setDeliveryMethod("EMAIL");
        assertEquals("EMAIL", request.getDeliveryMethod());
    }

    @Test
    @DisplayName("Test setting and getting startDate")
    void testStartDate() {
        LocalDateTime now = LocalDateTime.now();
        request.setStartDate(now);
        assertEquals(now, request.getStartDate());
    }

    @Test
    @DisplayName("Test setting null values")
    void testNullValues() {
        request.setReportId(null);
        request.setFrequency(null);
        request.setRecipients(null);
        request.setDeliveryMethod(null);
        request.setStartDate(null);
        assertNull(request.getReportId());
        assertNull(request.getFrequency());
        assertNull(request.getRecipients());
        assertNull(request.getDeliveryMethod());
        assertNull(request.getStartDate());
    }

    @Test
    @DisplayName("Test setting empty recipients list")
    void testEmptyRecipients() {
        request.setRecipients(Collections.emptyList());
        assertNotNull(request.getRecipients());
        assertTrue(request.getRecipients().isEmpty());
    }
}
