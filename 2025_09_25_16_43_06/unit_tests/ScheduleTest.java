package com.example.scheduler.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Schedule entity (getters/setters and basic object behavior).
 */
class ScheduleTest {
    @Test
    @DisplayName("Should set and get all fields correctly")
    void testGettersAndSetters() {
        Schedule schedule = new Schedule();
        User user = new User();
        user.setUserId(10L);
        Report report = new Report();
        report.setReportId(20L);
        schedule.setScheduleId(1L);
        schedule.setUser(user);
        schedule.setReport(report);
        schedule.setFrequency("daily");
        schedule.setStartDate(LocalDate.of(2025, 10, 1));
        schedule.setEndDate(LocalDate.of(2025, 12, 31));
        schedule.setDeliveryMethod("email");
        schedule.setStatus("Scheduled");
        schedule.setRecipients(Arrays.asList("user@example.com", "user2@example.com"));

        assertEquals(1L, schedule.getScheduleId());
        assertEquals(user, schedule.getUser());
        assertEquals(report, schedule.getReport());
        assertEquals("daily", schedule.getFrequency());
        assertEquals(LocalDate.of(2025, 10, 1), schedule.getStartDate());
        assertEquals(LocalDate.of(2025, 12, 31), schedule.getEndDate());
        assertEquals("email", schedule.getDeliveryMethod());
        assertEquals("Scheduled", schedule.getStatus());
        assertEquals(Arrays.asList("user@example.com", "user2@example.com"), schedule.getRecipients());
    }

    @Test
    @DisplayName("Should handle null and empty recipients list")
    void testRecipientsNullOrEmpty() {
        Schedule schedule = new Schedule();
        schedule.setRecipients(null);
        assertNull(schedule.getRecipients());
        schedule.setRecipients(Collections.emptyList());
        assertTrue(schedule.getRecipients().isEmpty());
    }
}
