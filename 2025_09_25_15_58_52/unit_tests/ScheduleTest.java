package com.example.scheduler.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Schedule entity.
 * Verifies all getters and setters, including edge cases.
 */
class ScheduleTest {
    private Schedule schedule;

    @BeforeEach
    void setUp() {
        schedule = new Schedule();
    }

    @Test
    @DisplayName("Test setting and getting id")
    void testId() {
        schedule.setId(1L);
        assertEquals(1L, schedule.getId());
    }

    @Test
    @DisplayName("Test setting and getting userId")
    void testUserId() {
        schedule.setUserId(2L);
        assertEquals(2L, schedule.getUserId());
    }

    @Test
    @DisplayName("Test setting and getting reportId")
    void testReportId() {
        schedule.setReportId(3L);
        assertEquals(3L, schedule.getReportId());
    }

    @Test
    @DisplayName("Test setting and getting frequency")
    void testFrequency() {
        schedule.setFrequency("DAILY");
        assertEquals("DAILY", schedule.getFrequency());
    }

    @Test
    @DisplayName("Test setting and getting deliveryMethod")
    void testDeliveryMethod() {
        schedule.setDeliveryMethod("EMAIL");
        assertEquals("EMAIL", schedule.getDeliveryMethod());
    }

    @Test
    @DisplayName("Test setting and getting startDate")
    void testStartDate() {
        LocalDateTime now = LocalDateTime.now();
        schedule.setStartDate(now);
        assertEquals(now, schedule.getStartDate());
    }

    @Test
    @DisplayName("Test setting and getting nextRun")
    void testNextRun() {
        LocalDateTime next = LocalDateTime.now().plusDays(1);
        schedule.setNextRun(next);
        assertEquals(next, schedule.getNextRun());
    }

    @Test
    @DisplayName("Test setting and getting status")
    void testStatus() {
        schedule.setStatus("SCHEDULED");
        assertEquals("SCHEDULED", schedule.getStatus());
    }

    @Test
    @DisplayName("Test setting and getting recipients")
    void testRecipients() {
        Recipient r1 = new Recipient();
        r1.setId(1L);
        Recipient r2 = new Recipient();
        r2.setId(2L);
        List<Recipient> recipients = Arrays.asList(r1, r2);
        schedule.setRecipients(recipients);
        assertEquals(recipients, schedule.getRecipients());
    }

    @Test
    @DisplayName("Test setting null values")
    void testNullValues() {
        schedule.setId(null);
        schedule.setUserId(null);
        schedule.setReportId(null);
        schedule.setFrequency(null);
        schedule.setDeliveryMethod(null);
        schedule.setStartDate(null);
        schedule.setNextRun(null);
        schedule.setStatus(null);
        schedule.setRecipients(null);
        assertNull(schedule.getId());
        assertNull(schedule.getUserId());
        assertNull(schedule.getReportId());
        assertNull(schedule.getFrequency());
        assertNull(schedule.getDeliveryMethod());
        assertNull(schedule.getStartDate());
        assertNull(schedule.getNextRun());
        assertNull(schedule.getStatus());
        assertNull(schedule.getRecipients());
    }

    @Test
    @DisplayName("Test setting empty recipients list")
    void testEmptyRecipients() {
        schedule.setRecipients(Collections.emptyList());
        assertNotNull(schedule.getRecipients());
        assertTrue(schedule.getRecipients().isEmpty());
    }
}
