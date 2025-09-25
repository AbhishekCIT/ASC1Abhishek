package com.example.scheduler.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ScheduleResponse model
 */
public class ScheduleResponseTest {
    private ScheduleResponse response;
    private final Long scheduleId = 1L;
    private final Long reportId = 2L;
    private final String frequency = "daily";
    private final String time = "08:00";
    private final List<String> recipients = Arrays.asList("user@example.com", "user2@example.com");
    private final LocalDateTime nextRun = LocalDateTime.now().plusDays(1);
    private final String status = "created";

    @BeforeEach
    void setUp() {
        response = new ScheduleResponse();
    }

    @Test
    @DisplayName("Test all-args constructor (with status) and getters")
    void testAllArgsConstructorWithStatus() {
        ScheduleResponse resp = new ScheduleResponse(scheduleId, status);
        assertEquals(scheduleId, resp.getScheduleId());
        assertEquals(status, resp.getStatus());
        assertNull(resp.getReportId());
        assertNull(resp.getFrequency());
        assertNull(resp.getTime());
        assertNull(resp.getRecipients());
        assertNull(resp.getNextRun());
    }

    @Test
    @DisplayName("Test all-args constructor (full) and getters")
    void testAllArgsConstructorFull() {
        ScheduleResponse resp = new ScheduleResponse(scheduleId, reportId, frequency, time, recipients, nextRun);
        assertEquals(scheduleId, resp.getScheduleId());
        assertEquals(reportId, resp.getReportId());
        assertEquals(frequency, resp.getFrequency());
        assertEquals(time, resp.getTime());
        assertEquals(recipients, resp.getRecipients());
        assertEquals(nextRun, resp.getNextRun());
        assertNull(resp.getStatus());
    }

    @Test
    @DisplayName("Test setters and getters")
    void testSettersAndGetters() {
        response.setScheduleId(scheduleId);
        response.setReportId(reportId);
        response.setFrequency(frequency);
        response.setTime(time);
        response.setRecipients(recipients);
        response.setNextRun(nextRun);
        response.setStatus(status);
        assertEquals(scheduleId, response.getScheduleId());
        assertEquals(reportId, response.getReportId());
        assertEquals(frequency, response.getFrequency());
        assertEquals(time, response.getTime());
        assertEquals(recipients, response.getRecipients());
        assertEquals(nextRun, response.getNextRun());
        assertEquals(status, response.getStatus());
    }

    @Test
    @DisplayName("Test default constructor initializes fields to null")
    void testDefaultConstructor() {
        ScheduleResponse resp = new ScheduleResponse();
        assertNull(resp.getScheduleId());
        assertNull(resp.getReportId());
        assertNull(resp.getFrequency());
        assertNull(resp.getTime());
        assertNull(resp.getRecipients());
        assertNull(resp.getNextRun());
        assertNull(resp.getStatus());
    }

    @Test
    @DisplayName("Test setting fields to null (edge case)")
    void testSetFieldsToNull() {
        response.setScheduleId(null);
        response.setReportId(null);
        response.setFrequency(null);
        response.setTime(null);
        response.setRecipients(null);
        response.setNextRun(null);
        response.setStatus(null);
        assertNull(response.getScheduleId());
        assertNull(response.getReportId());
        assertNull(response.getFrequency());
        assertNull(response.getTime());
        assertNull(response.getRecipients());
        assertNull(response.getNextRun());
        assertNull(response.getStatus());
    }
}
