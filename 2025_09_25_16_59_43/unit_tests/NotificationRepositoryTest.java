package com.example.scheduler.repository;

import com.example.scheduler.entity.Notification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NotificationRepository
 */
@DataJpaTest
public class NotificationRepositoryTest {
    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    @DisplayName("Test save and find Notification by ID")
    void testSaveAndFindById() {
        Notification notif = new Notification();
        notif.setScheduleId(100L);
        notif.setStatus("SUCCESS");
        notif.setMessage("Report delivered");
        notif.setCreatedAt(LocalDateTime.now());
        Notification saved = notificationRepository.save(notif);
        Optional<Notification> found = notificationRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals(saved.getId(), found.get().getId());
        assertEquals("SUCCESS", found.get().getStatus());
    }

    @Test
    @DisplayName("Test findById returns empty for non-existent ID (edge case)")
    void testFindByIdNotFound() {
        Optional<Notification> found = notificationRepository.findById(99999L);
        assertFalse(found.isPresent());
    }

    @Test
    @DisplayName("Test delete Notification")
    void testDeleteNotification() {
        Notification notif = new Notification();
        notif.setScheduleId(101L);
        notif.setStatus("FAILURE");
        notif.setMessage("Report failed");
        notif.setCreatedAt(LocalDateTime.now());
        Notification saved = notificationRepository.save(notif);
        Long id = saved.getId();
        notificationRepository.deleteById(id);
        assertFalse(notificationRepository.findById(id).isPresent());
    }
}
