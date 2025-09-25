package com.example.scheduler.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for User entity (getters/setters and basic object behavior).
 */
class UserTest {
    @Test
    @DisplayName("Should set and get all fields correctly")
    void testGettersAndSetters() {
        User user = new User();
        user.setUserId(10L);
        user.setEmail("user@example.com");
        Schedule s1 = new Schedule();
        Schedule s2 = new Schedule();
        user.setSchedules(Arrays.asList(s1, s2));
        assertEquals(10L, user.getUserId());
        assertEquals("user@example.com", user.getEmail());
        assertEquals(Arrays.asList(s1, s2), user.getSchedules());
    }

    @Test
    @DisplayName("Should handle null and empty schedules list")
    void testSchedulesNullOrEmpty() {
        User user = new User();
        user.setSchedules(null);
        assertNull(user.getSchedules());
        user.setSchedules(Collections.emptyList());
        assertTrue(user.getSchedules().isEmpty());
    }
}
