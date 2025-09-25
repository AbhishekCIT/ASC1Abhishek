package com.example.scheduler.controller;

import com.example.scheduler.model.NotificationRequest;
import com.example.scheduler.model.ReportGenerationRequest;
import com.example.scheduler.model.Schedule;
import com.example.scheduler.service.NotificationService;
import com.example.scheduler.service.ReportService;
import com.example.scheduler.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ScheduleController.
 */
class ScheduleControllerTest {
    @Mock
    private ScheduleService scheduleService;
    @Mock
    private NotificationService notificationService;
    @Mock
    private ReportService reportService;
    @InjectMocks
    private ScheduleController scheduleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test creating a schedule (happy path).
     */
    @Test
    @DisplayName("Should create schedule successfully")
    void testCreateSchedule_Success() {
        Schedule input = buildSampleSchedule();
        Schedule saved = buildSampleSchedule();
        saved.setScheduleId(1L);
        when(scheduleService.createSchedule(any(Schedule.class))).thenReturn(saved);
        ResponseEntity<?> response = scheduleController.createSchedule(input);
        assertEquals(saved, response.getBody());
        verify(scheduleService).createSchedule(input);
    }

    /**
     * Test editing a schedule (happy path).
     */
    @Test
    @DisplayName("Should edit schedule successfully")
    void testEditSchedule_Success() {
        Schedule input = buildSampleSchedule();
        Schedule edited = buildSampleSchedule();
        edited.setFrequency("weekly");
        when(scheduleService.editSchedule(eq(1L), any(Schedule.class))).thenReturn(edited);
        ResponseEntity<?> response = scheduleController.editSchedule(1L, input);
        assertEquals(edited, response.getBody());
        verify(scheduleService).editSchedule(1L, input);
    }

    /**
     * Test canceling a schedule (happy path).
     */
    @Test
    @DisplayName("Should cancel schedule successfully")
    void testCancelSchedule_Success() {
        Schedule canceled = buildSampleSchedule();
        canceled.setStatus("Cancelled");
        when(scheduleService.cancelSchedule(1L)).thenReturn(canceled);
        ResponseEntity<?> response = scheduleController.cancelSchedule(1L);
        assertEquals(canceled, response.getBody());
        verify(scheduleService).cancelSchedule(1L);
    }

    /**
     * Test viewing schedules (returns list).
     */
    @Test
    @DisplayName("Should return list of schedules for user")
    void testViewSchedules_Success() {
        List<Schedule> schedules = Arrays.asList(buildSampleSchedule(), buildSampleSchedule());
        when(scheduleService.getSchedulesForCurrentUser()).thenReturn(schedules);
        ResponseEntity<List<Schedule>> response = scheduleController.viewSchedules();
        assertEquals(schedules, response.getBody());
        verify(scheduleService).getSchedulesForCurrentUser();
    }

    /**
     * Test viewing schedules when none exist (edge case).
     */
    @Test
    @DisplayName("Should return empty list when no schedules exist")
    void testViewSchedules_Empty() {
        when(scheduleService.getSchedulesForCurrentUser()).thenReturn(Collections.emptyList());
        ResponseEntity<List<Schedule>> response = scheduleController.viewSchedules();
        assertTrue(response.getBody().isEmpty());
        verify(scheduleService).getSchedulesForCurrentUser();
    }

    /**
     * Test sending notification (happy path).
     */
    @Test
    @DisplayName("Should send notification successfully")
    void testSendNotification_Success() {
        NotificationRequest request = new NotificationRequest();
        request.setScheduleId(1L);
        request.setStatus("Delivered");
        request.setRecipients(Arrays.asList("user@example.com"));
        NotificationService.NotificationResponse mockResponse = new NotificationService.NotificationResponse();
        mockResponse.setNotificationId(201L);
        mockResponse.setStatus("Sent");
        when(notificationService.sendNotification(any(NotificationRequest.class))).thenReturn(mockResponse);
        ResponseEntity<?> response = scheduleController.sendNotification(request);
        assertEquals(mockResponse, response.getBody());
        verify(notificationService).sendNotification(request);
    }

    /**
     * Test generating report (happy path).
     */
    @Test
    @DisplayName("Should generate report successfully")
    void testGenerateReport_Success() {
        ReportGenerationRequest request = new ReportGenerationRequest();
        request.setScheduleId(1L);
        ReportService.ReportGenerationResponse mockResponse = new ReportService.ReportGenerationResponse();
        mockResponse.setReportUrl("/reports/1.pdf");
        mockResponse.setStatus("Generated");
        when(reportService.generateReport(any(ReportGenerationRequest.class))).thenReturn(mockResponse);
        ResponseEntity<?> response = scheduleController.generateReport(request);
        assertEquals(mockResponse, response.getBody());
        verify(reportService).generateReport(request);
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
        // User and Report can be set as null or mocked if needed
        schedule.setUser(null);
        schedule.setReport(null);
        return schedule;
    }
}
