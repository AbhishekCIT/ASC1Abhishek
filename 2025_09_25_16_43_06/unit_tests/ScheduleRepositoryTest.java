package com.example.scheduler.repository;

import com.example.scheduler.model.Schedule;
import com.example.scheduler.model.User;
import com.example.scheduler.model.Report;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic integration tests for ScheduleRepository using in-memory DB.
 */
@DataJpaTest
class ScheduleRepositoryTest {
    @Autowired
    private ScheduleRepository scheduleRepository;

    /**
     * Test saving and finding a Schedule entity.
     */
    @Test
    @DisplayName("Should save and retrieve schedule successfully")
    void testSaveAndFindSchedule() {
        Schedule schedule = buildSampleSchedule();
        Schedule saved = scheduleRepository.save(schedule);
        assertNotNull(saved.getScheduleId());
        Schedule found = scheduleRepository.findById(saved.getScheduleId()).orElse(null);
        assertNotNull(found);
        assertEquals(saved.getScheduleId(), found.getScheduleId());
        assertEquals("daily", found.getFrequency());
    }

    /**
     * Test findAll returns all schedules.
     */
    @Test
    @DisplayName("Should return all schedules")
    void testFindAll() {
        Schedule s1 = buildSampleSchedule();
        Schedule s2 = buildSampleSchedule();
        s2.setFrequency("weekly");
        scheduleRepository.saveAll(Arrays.asList(s1, s2));
        List<Schedule> all = scheduleRepository.findAll();
        assertTrue(all.size() >= 2);
    }

    /**
     * Helper to build a sample Schedule entity.
     */
    private Schedule buildSampleSchedule() {
        Schedule schedule = new Schedule();
        schedule.setFrequency("daily");
        schedule.setStartDate(LocalDate.of(2025, 10, 1));
        schedule.setEndDate(LocalDate.of(2025, 12, 31));
        schedule.setDeliveryMethod("email");
        schedule.setStatus("Scheduled");
        schedule.setRecipients(Arrays.asList("user@example.com"));
        // Minimal User and Report for FK constraints
        User user = new User();
        user.setEmail("user@example.com");
        schedule.setUser(user);
        Report report = new Report();
        report.setReportType("Sales");
        report.setFormat("PDF");
        schedule.setReport(report);
        return schedule;
    }
}
