package com.example.scheduler.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Schedule entity
 */
public class ScheduleTest {
    private Schedule schedule;
    private final Long id = 1L;
    private final Long reportId = 2L;
    private final String frequency = "daily";
    private final String time = "08:00";
    private final String recipients = "user@example.com,user2@example.com";
    private final LocalDateTime nextRun = LocalDateTime.now().plusDays(1);

    @BeforeEach
    void setUp() {
        schedule = new Schedule();
    }

    @Test
    @DisplayName("Test setters and getters for all fields")
    void testSettersAndGetters() {
        // Set values
        schedule.setId(id);
        schedule.setReportId(reportId);
        schedule.setFrequency(frequency);
        schedule.setTime(time);
        schedule.setRecipients(recipients);
        schedule.setNextRun(nextRun);
        // Assert values
        assertEquals(id, schedule.getId(), "ID should match");
        assertEquals(reportId, schedule.getReportId(), "Report ID should match");
        assertEquals(frequency, schedule.getFrequency(), "Frequency should match");
        assertEquals(time, schedule.getTime(), "Time should match");
        assertEquals(recipients, schedule.getRecipients(), "Recipients should match");
        assertEquals(nextRun, schedule.getNextRun(), "NextRun should match");
    }

    @Test
    @DisplayName("Test default constructor initializes fields to null")
    void testDefaultConstructor() {
        Schedule sched = new Schedule();
        assertNull(sched.getId(), "ID should be null by default");
        assertNull(sched.getReportId(), "Report ID should be null by default");
        assertNull(sched.getFrequency(), "Frequency should be null by default");
        assertNull(sched.getTime(), "Time should be null by default");
        assertNull(sched.getRecipients(), "Recipients should be null by default");
        assertNull(sched.getNextRun(), "NextRun should be null by default");
    }

    @Test
    @DisplayName("Test setting fields to null (edge case)")
    void testSetFieldsToNull() {
        schedule.setId(null);
        schedule.setReportId(null);
        schedule.setFrequency(null);
        schedule.setTime(null);
        schedule.setRecipients(null);
        schedule.setNextRun(null);
        assertNull(schedule.getId());
        assertNull(schedule.getReportId());
        assertNull(schedule.getFrequency());
        assertNull(schedule.getTime());
        assertNull(schedule.getRecipients());
        assertNull(schedule.getNextRun());
    }
}
