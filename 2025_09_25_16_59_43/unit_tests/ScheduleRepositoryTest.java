package com.example.scheduler.repository;

import com.example.scheduler.entity.Schedule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ScheduleRepository
 */
@DataJpaTest
public class ScheduleRepositoryTest {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    @DisplayName("Test save and find Schedule by ID")
    void testSaveAndFindById() {
        Schedule sched = new Schedule();
        sched.setReportId(100L);
        sched.setFrequency("daily");
        sched.setTime("08:00");
        sched.setRecipients("user@example.com");
        sched.setNextRun(LocalDateTime.now().plusDays(1));
        Schedule saved = scheduleRepository.save(sched);
        Optional<Schedule> found = scheduleRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals(saved.getId(), found.get().getId());
        assertEquals("daily", found.get().getFrequency());
    }

    @Test
    @DisplayName("Test findById returns empty for non-existent ID (edge case)")
    void testFindByIdNotFound() {
        Optional<Schedule> found = scheduleRepository.findById(99999L);
        assertFalse(found.isPresent());
    }

    @Test
    @DisplayName("Test delete Schedule")
    void testDeleteSchedule() {
        Schedule sched = new Schedule();
        sched.setReportId(101L);
        sched.setFrequency("weekly");
        sched.setTime("09:00");
        sched.setRecipients("user2@example.com");
        sched.setNextRun(LocalDateTime.now().plusWeeks(1));
        Schedule saved = scheduleRepository.save(sched);
        Long id = saved.getId();
        scheduleRepository.deleteById(id);
        assertFalse(scheduleRepository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Test existsByReportIdAndRecipients returns true for existing schedule")
    void testExistsByReportIdAndRecipientsTrue() {
        Schedule sched = new Schedule();
        sched.setReportId(200L);
        sched.setFrequency("monthly");
        sched.setTime("10:00");
        sched.setRecipients("user3@example.com");
        sched.setNextRun(LocalDateTime.now().plusMonths(1));
        scheduleRepository.save(sched);
        assertTrue(scheduleRepository.existsByReportIdAndRecipients(200L, "user3@example.com"));
    }

    @Test
    @DisplayName("Test existsByReportIdAndRecipients returns false for non-existent schedule (edge case)")
    void testExistsByReportIdAndRecipientsFalse() {
        assertFalse(scheduleRepository.existsByReportIdAndRecipients(999L, "nonexistent@example.com"));
    }
}
