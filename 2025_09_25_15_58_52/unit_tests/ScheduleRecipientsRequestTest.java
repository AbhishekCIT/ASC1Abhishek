package com.example.scheduler.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ScheduleRecipientsRequest DTO.
 * Verifies getter and setter, including edge cases.
 */
class ScheduleRecipientsRequestTest {
    private ScheduleRecipientsRequest request;

    @BeforeEach
    void setUp() {
        request = new ScheduleRecipientsRequest();
    }

    @Test
    @DisplayName("Test setting and getting recipients")
    void testRecipients() {
        List<String> recipients = Arrays.asList("a@example.com", "b@example.com");
        request.setRecipients(recipients);
        assertEquals(recipients, request.getRecipients());
    }

    @Test
    @DisplayName("Test setting null recipients")
    void testNullRecipients() {
        request.setRecipients(null);
        assertNull(request.getRecipients());
    }

    @Test
    @DisplayName("Test setting empty recipients list")
    void testEmptyRecipients() {
        request.setRecipients(Collections.emptyList());
        assertNotNull(request.getRecipients());
        assertTrue(request.getRecipients().isEmpty());
    }
}
