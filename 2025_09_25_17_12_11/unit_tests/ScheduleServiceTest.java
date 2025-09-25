package com.example.scheduler.service;

import com.example.scheduler.entity.Report;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.ScheduleRecipient;
import com.example.scheduler.model.ScheduleRequest;
import com.example.scheduler.model.ScheduleResponse;
import com.example.scheduler.repository.ReportRepository;
import com.example.scheduler.repository.ScheduleRecipientRepository;
import com.example.scheduler.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ScheduleService.
 */
class ScheduleServiceTest {
    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private ScheduleRecipientRepository scheduleRecipientRepository;
    @Mock
    private ReportRepository reportRepository;
    @Mock
    private ReportService reportService;
    @Mock
    private EmailService emailService;
    @Mock
    private NotificationService notificationService;
    @Mock
    private UserService userService;
    @InjectMocks
    private ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Create schedule - success")
    void testCreateScheduleSuccess() {
        ScheduleRequest request = new ScheduleRequest();
        request.setReportId(1L);
        request.setFrequency("DAILY");
        request.setTime("09:00");
        request.setRecipients(Arrays.asList("user1@abc.com"));
        when(scheduleRepository.existsByReportIdAndFrequencyAndTimeAndRecipients(anyLong(), anyString(), anyString(), anyList())).thenReturn(false);
        Schedule saved = new Schedule();
        saved.setId(101L);
        saved.setReportId(1L);
        saved.setFrequency("DAILY");
        saved.setTime(LocalTime.of(9, 0));
        saved.setStatus("CREATED");
        saved.setCreatedAt(LocalDateTime.now());
        saved.setUpdatedAt(LocalDateTime.now());
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(saved);
        doNothing().when(notificationService).sendConfirmation(anyLong(), anyString());
        when(scheduleRecipientRepository.saveAll(anyList())).thenReturn(Collections.emptyList());

        ScheduleResponse response = scheduleService.createSchedule(request);
        assertEquals(101L, response.getScheduleId());
        assertEquals("CREATED", response.getStatus());
        verify(notificationService).sendConfirmation(101L, "CREATED");
    }

    @Test
    @DisplayName("Create schedule - duplicate schedule")
    void testCreateScheduleDuplicate() {
        ScheduleRequest request = new ScheduleRequest();
        request.setReportId(1L);
        request.setFrequency("DAILY");
        request.setTime("09:00");
        request.setRecipients(Arrays.asList("user1@abc.com"));
        when(scheduleRepository.existsByReportIdAndFrequencyAndTimeAndRecipients(anyLong(), anyString(), anyString(), anyList())).thenReturn(true);
        assertThrows(RuntimeException.class, () -> scheduleService.createSchedule(request));
    }

    @Test
    @DisplayName("Edit schedule - success")
    void testEditScheduleSuccess() {
        ScheduleRequest request = new ScheduleRequest();
        request.setFrequency("WEEKLY");
        request.setTime("10:00");
        request.setRecipients(Arrays.asList("user2@abc.com"));
        Schedule schedule = new Schedule();
        schedule.setId(101L);
        schedule.setReportId(1L);
        schedule.setFrequency("DAILY");
        schedule.setTime(LocalTime.of(9, 0));
        schedule.setStatus("CREATED");
        when(scheduleRepository.findById(101L)).thenReturn(Optional.of(schedule));
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);
        doNothing().when(scheduleRecipientRepository).deleteByScheduleId(101L);
        when(scheduleRecipientRepository.saveAll(anyList())).thenReturn(Collections.emptyList());
        doNothing().when(notificationService).sendConfirmation(anyLong(), anyString());

        ScheduleResponse response = scheduleService.editSchedule(101L, request);
        assertEquals(101L, response.getScheduleId());
        assertEquals("UPDATED", response.getStatus());
        verify(notificationService).sendConfirmation(101L, "UPDATED");
    }

    @Test
    @DisplayName("Edit schedule - not found")
    void testEditScheduleNotFound() {
        ScheduleRequest request = new ScheduleRequest();
        request.setFrequency("DAILY");
        request.setTime("09:00");
        request.setRecipients(Arrays.asList("user1@abc.com"));
        when(scheduleRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> scheduleService.editSchedule(999L, request));
    }

    @Test
    @DisplayName("Delete schedule - success")
    void testDeleteScheduleSuccess() {
        Schedule schedule = new Schedule();
        schedule.setId(101L);
        when(scheduleRepository.findById(101L)).thenReturn(Optional.of(schedule));
        doNothing().when(scheduleRecipientRepository).deleteByScheduleId(101L);
        doNothing().when(notificationService).sendConfirmation(anyLong(), anyString());
        doNothing().when(scheduleRepository).delete(schedule);

        ScheduleResponse response = scheduleService.deleteSchedule(101L);
        assertEquals(101L, response.getScheduleId());
        assertEquals("DELETED", response.getStatus());
        verify(notificationService).sendConfirmation(101L, "DELETED");
    }

    @Test
    @DisplayName("Delete schedule - not found")
    void testDeleteScheduleNotFound() {
        when(scheduleRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> scheduleService.deleteSchedule(999L));
    }

    @Test
    @DisplayName("List schedules - success")
    void testListSchedulesSuccess() {
        Schedule s1 = new Schedule();
        s1.setId(101L);
        s1.setReportId(1L);
        s1.setFrequency("DAILY");
        s1.setTime(LocalTime.of(9, 0));
        s1.setStatus("CREATED");
        Schedule s2 = new Schedule();
        s2.setId(102L);
        s2.setReportId(2L);
        s2.setFrequency("WEEKLY");
        s2.setTime(LocalTime.of(10, 0));
        s2.setStatus("CREATED");
        when(scheduleRepository.findByUserId(123L)).thenReturn(Arrays.asList(s1, s2));
        when(scheduleRecipientRepository.findByScheduleId(101L)).thenReturn(Arrays.asList(new ScheduleRecipient(s1, "user1@abc.com")));
        when(scheduleRecipientRepository.findByScheduleId(102L)).thenReturn(Arrays.asList(new ScheduleRecipient(s2, "user2@abc.com")));

        List<ScheduleResponse> responses = scheduleService.listSchedules(123L);
        assertEquals(2, responses.size());
        assertEquals(101L, responses.get(0).getScheduleId());
        assertEquals(102L, responses.get(1).getScheduleId());
        assertEquals("user1@abc.com", responses.get(0).getRecipients().get(0));
        assertEquals("user2@abc.com", responses.get(1).getRecipients().get(0));
    }

    @Test
    @DisplayName("Trigger schedule - success")
    void testTriggerScheduleSuccess() {
        Schedule schedule = new Schedule();
        schedule.setId(101L);
        schedule.setReportId(1L);
        schedule.setFrequency("DAILY");
        schedule.setTime(LocalTime.of(9, 0));
        schedule.setStatus("CREATED");
        Report report = new Report();
        report.setId(1L);
        when(scheduleRepository.findById(101L)).thenReturn(Optional.of(schedule));
        when(reportRepository.findById(1L)).thenReturn(Optional.of(report));
        when(scheduleRecipientRepository.findByScheduleId(101L)).thenReturn(Arrays.asList(new ScheduleRecipient(schedule, "user1@abc.com")));
        when(reportService.generateReport(report)).thenReturn(new byte[0]);
        doNothing().when(emailService).sendReport(any(byte[].class), anyList());
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);

        ScheduleResponse response = scheduleService.triggerSchedule(101L);
        assertEquals(101L, response.getScheduleId());
        assertEquals("TRIGGERED", response.getStatus());
    }

    @Test
    @DisplayName("Trigger schedule - schedule not found")
    void testTriggerScheduleNotFound() {
        when(scheduleRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> scheduleService.triggerSchedule(999L));
    }

    @Test
    @DisplayName("Trigger schedule - report not found")
    void testTriggerScheduleReportNotFound() {
        Schedule schedule = new Schedule();
        schedule.setId(101L);
        schedule.setReportId(1L);
        when(scheduleRepository.findById(101L)).thenReturn(Optional.of(schedule));
        when(reportRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> scheduleService.triggerSchedule(101L));
    }

    @Test
    @DisplayName("Create schedule - invalid frequency")
    void testCreateScheduleInvalidFrequency() {
        ScheduleRequest request = new ScheduleRequest();
        request.setReportId(1L);
        request.setFrequency("INVALID");
        request.setTime("09:00");
        request.setRecipients(Arrays.asList("user1@abc.com"));
        assertThrows(RuntimeException.class, () -> scheduleService.createSchedule(request));
    }

    @Test
    @DisplayName("Create schedule - invalid time")
    void testCreateScheduleInvalidTime() {
        ScheduleRequest request = new ScheduleRequest();
        request.setReportId(1L);
        request.setFrequency("DAILY");
        request.setTime("invalid_time");
        request.setRecipients(Arrays.asList("user1@abc.com"));
        assertThrows(RuntimeException.class, () -> scheduleService.createSchedule(request));
    }

    @Test
    @DisplayName("Create schedule - invalid email")
    void testCreateScheduleInvalidEmail() {
        ScheduleRequest request = new ScheduleRequest();
        request.setReportId(1L);
        request.setFrequency("DAILY");
        request.setTime("09:00");
        request.setRecipients(Arrays.asList("invalid_email"));
        assertThrows(RuntimeException.class, () -> scheduleService.createSchedule(request));
    }
}
