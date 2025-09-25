package com.example.scheduler.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Recipient entity.
 * Verifies all getters and setters, including edge cases.
 */
class RecipientTest {
    private Recipient recipient;

    @BeforeEach
    void setUp() {
        recipient = new Recipient();
    }

    @Test
    @DisplayName("Test setting and getting id")
    void testId() {
        recipient.setId(10L);
        assertEquals(10L, recipient.getId());
    }

    @Test
    @DisplayName("Test setting and getting schedule")
    void testSchedule() {
        Schedule schedule = new Schedule();
        schedule.setId(20L);
        recipient.setSchedule(schedule);
        assertEquals(schedule, recipient.getSchedule());
    }

    @Test
    @DisplayName("Test setting and getting email")
    void testEmail() {
        recipient.setEmail("test@example.com");
        assertEquals("test@example.com", recipient.getEmail());
    }

    @Test
    @DisplayName("Test setting null values")
    void testNullValues() {
        recipient.setId(null);
        recipient.setSchedule(null);
        recipient.setEmail(null);
        assertNull(recipient.getId());
        assertNull(recipient.getSchedule());
        assertNull(recipient.getEmail());
    }

    @Test
    @DisplayName("Test setting empty email string")
    void testEmptyEmail() {
        recipient.setEmail("");
        assertEquals("", recipient.getEmail());
    }
}
