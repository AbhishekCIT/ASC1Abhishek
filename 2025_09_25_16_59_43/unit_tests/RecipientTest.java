package com.example.scheduler.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Recipient entity
 */
public class RecipientTest {
    private Recipient recipient;
    private final Long id = 1L;
    private final String email = "user@example.com";
    private final Long scheduleId = 10L;

    @BeforeEach
    void setUp() {
        recipient = new Recipient();
    }

    @Test
    @DisplayName("Test setters and getters for all fields")
    void testSettersAndGetters() {
        // Set values
        recipient.setId(id);
        recipient.setEmail(email);
        recipient.setScheduleId(scheduleId);
        // Assert values
        assertEquals(id, recipient.getId(), "ID should match");
        assertEquals(email, recipient.getEmail(), "Email should match");
        assertEquals(scheduleId, recipient.getScheduleId(), "Schedule ID should match");
    }

    @Test
    @DisplayName("Test default constructor initializes fields to null")
    void testDefaultConstructor() {
        Recipient rec = new Recipient();
        assertNull(rec.getId(), "ID should be null by default");
        assertNull(rec.getEmail(), "Email should be null by default");
        assertNull(rec.getScheduleId(), "Schedule ID should be null by default");
    }

    @Test
    @DisplayName("Test setting fields to null (edge case)")
    void testSetFieldsToNull() {
        recipient.setId(null);
        recipient.setEmail(null);
        recipient.setScheduleId(null);
        assertNull(recipient.getId());
        assertNull(recipient.getEmail());
        assertNull(recipient.getScheduleId());
    }
}
