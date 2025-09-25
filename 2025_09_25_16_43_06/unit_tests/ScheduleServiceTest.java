package com.example.scheduler.service;

import com.example.scheduler.model.Schedule;
import com.example.scheduler.repository.ScheduleRepository;
import com.example.scheduler.util.EmailValidatorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ScheduleService.
 */
class ScheduleServiceTest {
    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private EmailValidatorUtil emailValidatorUtil;
    @InjectMocks
    private ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test creating a schedule with valid data (happy path).
     */
    @Test
    @DisplayName("Should create schedule successfully with valid data")
    void testCreateSchedule_Success() {
        Schedule input = buildSampleSchedule();
        Schedule saved = buildSampleSchedule();
        saved.setScheduleId(1L);
        when(emailValidatorUtil.isValid(anyString())).thenReturn(true);
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(saved);
        Schedule result = scheduleService.createSchedule(input);
        assertEquals(saved, result);
        assertEquals("Scheduled", result.getStatus());
        verify(scheduleRepository).save(input);
    }

    /**
     * Test creating a schedule with invalid frequency (error case).
     */
    @Test
    @DisplayName("Should throw exception for invalid frequency")
    void testCreateSchedule_InvalidFrequency() {
        Schedule input = buildSampleSchedule();
        input.setFrequency("hourly");
        when(emailValidatorUtil.isValid(anyString())).thenReturn(true);
        Exception ex = assertThrows(RuntimeException.class, () -> scheduleService.createSchedule(input));
        assertEquals("Invalid frequency option.", ex.getMessage());
    }

    /**
     * Test creating a schedule with invalid recipient email (error case).
     */
    @Test
    @DisplayName("Should throw exception for invalid recipient email")
    void testCreateSchedule_InvalidEmail() {
        Schedule input = buildSampleSchedule();
        when(emailValidatorUtil.isValid(anyString())).thenReturn(false);
        Exception ex = assertThrows(RuntimeException.class, () -> scheduleService.createSchedule(input));
        assertEquals("Invalid recipient email address.", ex.getMessage());
    }

    /**
     * Test creating a schedule with empty recipients (error case).
     */
    @Test
    @DisplayName("Should throw exception for empty recipients list")
    void testCreateSchedule_EmptyRecipients() {
        Schedule input = buildSampleSchedule();
        input.setRecipients(Collections.emptyList());
        Exception ex = assertThrows(RuntimeException.class, () -> scheduleService.createSchedule(input));
        assertEquals("Recipients must be provided.", ex.getMessage());
    }

    /**
     * Test creating a schedule with start date after end date (error case).
     */
    @Test
    @DisplayName("Should throw exception for start date after end date")
    void testCreateSchedule_StartDateAfterEndDate() {
        Schedule input = buildSampleSchedule();
        input.setStartDate(LocalDate.of(2025, 12, 31));
        input.setEndDate(LocalDate.of(2025, 10, 1));
        when(emailValidatorUtil.isValid(anyString())).thenReturn(true);
        Exception ex = assertThrows(RuntimeException.class, () -> scheduleService.createSchedule(input));
        assertEquals("Start date after end date.", ex.getMessage());
    }

    /**
     * Test editing a schedule with valid data (happy path).
     */
    @Test
    @DisplayName("Should edit schedule successfully with valid data")
    void testEditSchedule_Success() {
        Schedule existing = buildSampleSchedule();
        existing.setScheduleId(1L);
        Schedule input = buildSampleSchedule();
        input.setFrequency("weekly");
        input.setRecipients(Arrays.asList("user2@example.com"));
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(emailValidatorUtil.isValid(anyString())).thenReturn(true);
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(existing);
        Schedule result = scheduleService.editSchedule(1L, input);
        assertEquals("weekly", result.getFrequency());
        assertEquals(Arrays.asList("user2@example.com"), result.getRecipients());
        verify(scheduleRepository).save(existing);
    }

    /**
     * Test editing a schedule that does not exist (error case).
     */
    @Test
    @DisplayName("Should throw exception when editing non-existent schedule")
    void testEditSchedule_NotFound() {
        Schedule input = buildSampleSchedule();
        when(scheduleRepository.findById(1L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(RuntimeException.class, () -> scheduleService.editSchedule(1L, input));
        assertEquals("Schedule not found.", ex.getMessage());
    }

    /**
     * Test editing a schedule with invalid frequency (error case).
     */
    @Test
    @DisplayName("Should throw exception for invalid frequency on edit")
    void testEditSchedule_InvalidFrequency() {
        Schedule existing = buildSampleSchedule();
        existing.setScheduleId(1L);
        Schedule input = buildSampleSchedule();
        input.setFrequency("hourly");
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(existing));
        Exception ex = assertThrows(RuntimeException.class, () -> scheduleService.editSchedule(1L, input));
        assertEquals("Invalid frequency option.", ex.getMessage());
    }

    /**
     * Test editing a schedule with invalid recipient email (error case).
     */
    @Test
    @DisplayName("Should throw exception for invalid recipient email on edit")
    void testEditSchedule_InvalidEmail() {
        Schedule existing = buildSampleSchedule();
        existing.setScheduleId(1L);
        Schedule input = buildSampleSchedule();
        input.setRecipients(Arrays.asList("bademail"));
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(emailValidatorUtil.isValid(anyString())).thenReturn(false);
        Exception ex = assertThrows(RuntimeException.class, () -> scheduleService.editSchedule(1L, input));
        assertEquals("Invalid recipient email address.", ex.getMessage());
    }

    /**
     * Test canceling a schedule (happy path).
     */
    @Test
    @DisplayName("Should cancel schedule successfully")
    void testCancelSchedule_Success() {
        Schedule existing = buildSampleSchedule();
        existing.setScheduleId(1L);
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(existing);
        Schedule result = scheduleService.cancelSchedule(1L);
        assertEquals("Cancelled", result.getStatus());
        verify(scheduleRepository).save(existing);
    }

    /**
     * Test canceling a schedule that does not exist (error case).
     */
    @Test
    @DisplayName("Should throw exception when canceling non-existent schedule")
    void testCancelSchedule_NotFound() {
        when(scheduleRepository.findById(1L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(RuntimeException.class, () -> scheduleService.cancelSchedule(1L));
        assertEquals("Schedule not found.", ex.getMessage());
    }

    /**
     * Test getting all schedules for current user (returns list).
     */
    @Test
    @DisplayName("Should return all schedules for current user")
    void testGetSchedulesForCurrentUser() {
        List<Schedule> schedules = Arrays.asList(buildSampleSchedule(), buildSampleSchedule());
        when(scheduleRepository.findAll()).thenReturn(schedules);
        List<Schedule> result = scheduleService.getSchedulesForCurrentUser();
        assertEquals(schedules, result);
        verify(scheduleRepository).findAll();
    }

    /**
     * Helper to build a sample Schedule object.
     */
    private Schedule buildSampleSchedule() {
        Schedule schedule = new Schedule();
        schedule.setScheduleId(null);
        schedule.setFrequency("daily");
        schedule.setStartDate(LocalDate.of(2025, 10, 1));
        schedule.setEndDate(LocalDate.of(2025, 12, 31));
        schedule.setDeliveryMethod("email");
        schedule.setStatus("Scheduled");
        schedule.setRecipients(Arrays.asList("user@example.com"));
        schedule.setUser(null);
        schedule.setReport(null);
        return schedule;
    }
}
