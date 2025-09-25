package com.example.scheduler.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Notification entity
 */
public class NotificationTest {
    private Notification notification;
    private final Long id = 1L;
    private final Long scheduleId = 10L;
    private final String status = "SUCCESS";
    private final String message = "Report delivered";
    private final LocalDateTime createdAt = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        notification = new Notification();
    }

    @Test
    @DisplayName("Test setters and getters for all fields")
    void testSettersAndGetters() {
        // Set values
        notification.setId(id);
        notification.setScheduleId(scheduleId);
        notification.setStatus(status);
        notification.setMessage(message);
        notification.setCreatedAt(createdAt);
        // Assert values
        assertEquals(id, notification.getId(), "ID should match");
        assertEquals(scheduleId, notification.getScheduleId(), "Schedule ID should match");
        assertEquals(status, notification.getStatus(), "Status should match");
        assertEquals(message, notification.getMessage(), "Message should match");
        assertEquals(createdAt, notification.getCreatedAt(), "CreatedAt should match");
    }

    @Test
    @DisplayName("Test default constructor initializes fields to null")
    void testDefaultConstructor() {
        Notification notif = new Notification();
        assertNull(notif.getId(), "ID should be null by default");
        assertNull(notif.getScheduleId(), "Schedule ID should be null by default");
        assertNull(notif.getStatus(), "Status should be null by default");
        assertNull(notif.getMessage(), "Message should be null by default");
        assertNull(notif.getCreatedAt(), "CreatedAt should be null by default");
    }

    @Test
    @DisplayName("Test setting fields to null (edge case)")
    void testSetFieldsToNull() {
        notification.setId(null);
        notification.setScheduleId(null);
        notification.setStatus(null);
        notification.setMessage(null);
        notification.setCreatedAt(null);
        assertNull(notification.getId());
        assertNull(notification.getScheduleId());
        assertNull(notification.getStatus());
        assertNull(notification.getMessage());
        assertNull(notification.getCreatedAt());
    }
}
