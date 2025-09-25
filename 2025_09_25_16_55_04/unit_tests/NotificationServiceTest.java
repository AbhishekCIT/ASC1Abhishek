package com.example.scheduling.service;

import com.example.scheduling.entity.ScheduleEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NotificationService.
 */
class NotificationServiceTest {
    private final NotificationService notificationService = new NotificationService();

    /**
     * Test that sendConfirmationEmail does not throw and accepts a valid ScheduleEntity.
     */
    @Test
    @DisplayName("sendConfirmationEmail: should accept ScheduleEntity and not throw")
    void testSendConfirmationEmail() {
        ScheduleEntity entity = new ScheduleEntity();
        entity.setId(1L);
        entity.setFrequency("daily");
        entity.setTime("14:00");
        entity.setReportType("PDF");
        entity.setRecipients("user@example.com");
        entity.setCreatedBy(1L);
        entity.setUpdatedAt(LocalDateTime.now());
        assertDoesNotThrow(() -> notificationService.sendConfirmationEmail(entity));
    }
}
