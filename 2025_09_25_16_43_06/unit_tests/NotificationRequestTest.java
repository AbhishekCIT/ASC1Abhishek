package com.example.scheduler.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NotificationRequest (getters/setters and edge cases).
 */
class NotificationRequestTest {
    @Test
    @DisplayName("Should set and get all fields correctly")
    void testGettersAndSetters() {
        NotificationRequest req = new NotificationRequest();
        req.setScheduleId(1L);
        req.setStatus("Delivered");
        req.setRecipients(Arrays.asList("user@example.com", "user2@example.com"));
        assertEquals(1L, req.getScheduleId());
        assertEquals("Delivered", req.getStatus());
        assertEquals(Arrays.asList("user@example.com", "user2@example.com"), req.getRecipients());
    }

    @Test
    @DisplayName("Should handle null and empty recipients list")
    void testRecipientsNullOrEmpty() {
        NotificationRequest req = new NotificationRequest();
        req.setRecipients(null);
        assertNull(req.getRecipients());
        req.setRecipients(Collections.emptyList());
        assertTrue(req.getRecipients().isEmpty());
    }
}
