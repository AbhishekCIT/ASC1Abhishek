package com.example.scheduler.service;

import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.User;
import com.example.scheduler.model.ScheduleRequest;
import com.example.scheduler.model.ScheduleResponse;
import com.example.scheduler.model.TriggerReportResponse;
import com.example.scheduler.repository.ScheduleRepository;
import com.example.scheduler.util.EmailUtil;
import com.example.scheduler.util.TimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.scheduling.TaskScheduler;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ScheduleService.
 */
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private ReportService reportService;
    @Mock
    private DeliveryService deliveryService;
    @Mock
    private NotificationService notificationService;
    @Mock
    private TaskScheduler taskScheduler;

    @InjectMocks
    private ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test creating a schedule with valid input.
     */
    @Test
    void testCreateSchedule_Success() {
        ScheduleRequest request = new ScheduleRequest();
        request.setReportId("report-1");
        request.setFrequency("DAILY");
        request.setTime("08:00");
        request.setDeliveryMethods(Collections.singletonList("EMAIL"));
        request.setRecipients(Collections.singletonList("user@domain.com"));
        when(reportService.isValidReportTemplate("report-1")).thenReturn(true);
        Schedule savedSchedule = new Schedule();
        savedSchedule.setScheduleId("sch-1");
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(savedSchedule);
        ScheduleResponse response = scheduleService.createSchedule(request);
        assertEquals("sch-1", response.getScheduleId());
        assertEquals("CREATED", response.getStatus());
        verify(scheduleRepository, times(1)).save(any(Schedule.class));
    }

    /**
     * Test creating a schedule with invalid report template (validation failure).
     */
    @Test
    void testCreateSchedule_InvalidReportTemplate() {
        ScheduleRequest request = new ScheduleRequest();
        request.setReportId("invalid");
        request.setFrequency("DAILY");
        request.setTime("08:00");
        request.setDeliveryMethods(Collections.singletonList("EMAIL"));
        request.setRecipients(Collections.singletonList("user@domain.com"));
        when(reportService.isValidReportTemplate("invalid")).thenReturn(false);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> scheduleService.createSchedule(request));
        assertEquals("Invalid report template", ex.getMessage());
    }

    /**
     * Test creating a schedule with invalid frequency (validation failure).
     */
    @Test
    void testCreateSchedule_InvalidFrequency() {
        ScheduleRequest request = new ScheduleRequest();
        request.setReportId("report-1");
        request.setFrequency("YEARLY");
        request.setTime("08:00");
        request.setDeliveryMethods(Collections.singletonList("EMAIL"));
        request.setRecipients(Collections.singletonList("user@domain.com"));
        when(reportService.isValidReportTemplate("report-1")).thenReturn(true);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> scheduleService.createSchedule(request));
        assertEquals("Invalid frequency", ex.getMessage());
    }

    /**
     * Test creating a schedule with invalid time (validation failure).
     */
    @Test
    void testCreateSchedule_InvalidTime() {
        ScheduleRequest request = new ScheduleRequest();
        request.setReportId("report-1");
        request.setFrequency("DAILY");
        request.setTime("25:00");
        request.setDeliveryMethods(Collections.singletonList("EMAIL"));
        request.setRecipients(Collections.singletonList("user@domain.com"));
        when(reportService.isValidReportTemplate("report-1")).thenReturn(true);
        // Mock static method TimeUtil.isValidTime
        try (MockedStatic<TimeUtil> timeUtilMock = mockStatic(TimeUtil.class)) {
            timeUtilMock.when(() -> TimeUtil.isValidTime("25:00")).thenReturn(false);
            Exception ex = assertThrows(IllegalArgumentException.class, () -> scheduleService.createSchedule(request));
            assertEquals("Invalid time", ex.getMessage());
        }
    }

    /**
     * Test creating a schedule with invalid email (validation failure).
     */
    @Test
    void testCreateSchedule_InvalidEmail() {
        ScheduleRequest request = new ScheduleRequest();
        request.setReportId("report-1");
        request.setFrequency("DAILY");
        request.setTime("08:00");
        request.setDeliveryMethods(Collections.singletonList("EMAIL"));
        request.setRecipients(Collections.singletonList("bademail"));
        when(reportService.isValidReportTemplate("report-1")).thenReturn(true);
        // Mock static method TimeUtil.isValidTime
        try (MockedStatic<TimeUtil> timeUtilMock = mockStatic(TimeUtil.class);
             MockedStatic<EmailUtil> emailUtilMock = mockStatic(EmailUtil.class)) {
            timeUtilMock.when(() -> TimeUtil.isValidTime("08:00")).thenReturn(true);
            emailUtilMock.when(() -> EmailUtil.isValidEmail("bademail")).thenReturn(false);
            Exception ex = assertThrows(IllegalArgumentException.class, () -> scheduleService.createSchedule(request));
            assertEquals("Invalid email address: bademail", ex.getMessage());
        }
    }

    /**
     * Test creating a schedule with no delivery methods (validation failure).
     */
    @Test
    void testCreateSchedule_NoDeliveryMethods() {
        ScheduleRequest request = new ScheduleRequest();
        request.setReportId("report-1");
        request.setFrequency("DAILY");
        request.setTime("08:00");
        request.setDeliveryMethods(Collections.emptyList());
        request.setRecipients(Collections.singletonList("user@domain.com"));
        when(reportService.isValidReportTemplate("report-1")).thenReturn(true);
        try (MockedStatic<TimeUtil> timeUtilMock = mockStatic(TimeUtil.class)) {
            timeUtilMock.when(() -> TimeUtil.isValidTime("08:00")).thenReturn(true);
            Exception ex = assertThrows(IllegalArgumentException.class, () -> scheduleService.createSchedule(request));
            assertEquals("At least one delivery method required", ex.getMessage());
        }
    }

    /**
     * Test getSchedulesForCurrentUser returns mapped responses.
     */
    @Test
    void testGetSchedulesForCurrentUser() {
        User user = new User("user-1", "user@domain.com");
        Schedule s1 = new Schedule();
        s1.setScheduleId("sch-1");
        s1.setReportId("report-1");
        s1.setFrequency("DAILY");
        s1.setTime("08:00");
        s1.setDeliveryMethods(Collections.singletonList("EMAIL"));
        s1.setStatus("ACTIVE");
        Schedule s2 = new Schedule();
        s2.setScheduleId("sch-2");
        s2.setReportId("report-2");
        s2.setFrequency("WEEKLY");
        s2.setTime("09:00");
        s2.setDeliveryMethods(Collections.singletonList("EMAIL"));
        s2.setStatus("ACTIVE");
        when(scheduleRepository.findByUser(any(User.class))).thenReturn(Arrays.asList(s1, s2));
        List<ScheduleResponse> responses = scheduleService.getSchedulesForCurrentUser();
        assertEquals(2, responses.size());
        assertEquals("sch-1", responses.get(0).getScheduleId());
        assertEquals("sch-2", responses.get(1).getScheduleId());
    }

    /**
     * Test triggerReport success scenario.
     */
    @Test
    void testTriggerReport_Success() {
        Schedule schedule = new Schedule();
        schedule.setScheduleId("sch-1");
        schedule.setReportId("report-1");
        when(scheduleRepository.findById("sch-1")).thenReturn(Optional.of(schedule));
        when(reportService.generateReport("report-1")).thenReturn(new byte[0]);
        doNothing().when(deliveryService).deliverReport(any(Schedule.class), any(byte[].class));
        doNothing().when(notificationService).notifyUser(any(Schedule.class), eq(true), isNull());
        TriggerReportResponse response = scheduleService.triggerReport("sch-1");
        assertEquals("SUCCESS", response.getDeliveryStatus());
        assertNotNull(response.getTimestamp());
    }

    /**
     * Test triggerReport when schedule not found (edge case).
     */
    @Test
    void testTriggerReport_ScheduleNotFound() {
        when(scheduleRepository.findById("sch-404")).thenReturn(Optional.empty());
        Exception ex = assertThrows(RuntimeException.class, () -> scheduleService.triggerReport("sch-404"));
        assertEquals("Schedule not found", ex.getMessage());
    }

    /**
     * Test triggerReport when report generation or delivery fails (error scenario).
     */
    @Test
    void testTriggerReport_ReportGenerationFails() {
        Schedule schedule = new Schedule();
        schedule.setScheduleId("sch-1");
        schedule.setReportId("report-1");
        when(scheduleRepository.findById("sch-1")).thenReturn(Optional.of(schedule));
        when(reportService.generateReport("report-1")).thenThrow(new RuntimeException("Report error"));
        doNothing().when(notificationService).notifyUser(any(Schedule.class), eq(false), anyString());
        Exception ex = assertThrows(RuntimeException.class, () -> scheduleService.triggerReport("sch-1"));
        assertTrue(ex.getMessage().contains("Failed to generate or deliver report"));
    }
}
