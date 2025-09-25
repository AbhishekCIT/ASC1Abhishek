package com.example.scheduler.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ScheduleRequest model
 */
public class ScheduleRequestTest {
    private ScheduleRequest request;
    private final Long reportId = 1L;
    private final String frequency = "daily";
    private final String time = "08:00";
    private final List<String> recipients = Arrays.asList("user@example.com", "user2@example.com");

    @BeforeEach
    void setUp() {
        request = new ScheduleRequest();
    }

    @Test
    @DisplayName("Test all-args constructor and getters")
    void testAllArgsConstructorAndGetters() {
        ScheduleRequest req = new ScheduleRequest(reportId, frequency, time, recipients);
        assertEquals(reportId, req.getReportId());
        assertEquals(frequency, req.getFrequency());
        assertEquals(time, req.getTime());
        assertEquals(recipients, req.getRecipients());
    }

    @Test
    @DisplayName("Test setters and getters")
    void testSettersAndGetters() {
        request.setReportId(reportId);
        request.setFrequency(frequency);
        request.setTime(time);
        request.setRecipients(recipients);
        assertEquals(reportId, request.getReportId());
        assertEquals(frequency, request.getFrequency());
        assertEquals(time, request.getTime());
        assertEquals(recipients, request.getRecipients());
    }

    @Test
    @DisplayName("Test default constructor initializes fields to null")
    void testDefaultConstructor() {
        ScheduleRequest req = new ScheduleRequest();
        assertNull(req.getReportId());
        assertNull(req.getFrequency());
        assertNull(req.getTime());
        assertNull(req.getRecipients());
    }

    @Test
    @DisplayName("Test setting fields to null (edge case)")
    void testSetFieldsToNull() {
        request.setReportId(null);
        request.setFrequency(null);
        request.setTime(null);
        request.setRecipients(null);
        assertNull(request.getReportId());
        assertNull(request.getFrequency());
        assertNull(request.getTime());
        assertNull(request.getRecipients());
    }

    @Test
    @DisplayName("Test recipients with empty list (boundary case)")
    void testRecipientsEmptyList() {
        request.setRecipients(Collections.emptyList());
        assertNotNull(request.getRecipients());
        assertTrue(request.getRecipients().isEmpty());
    }
}
