package com.example.scheduler.controller;

import com.example.scheduler.model.ScheduleRequest;
import com.example.scheduler.model.ScheduleResponse;
import com.example.scheduler.model.TriggerReportResponse;
import com.example.scheduler.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ScheduleController.
 */
public class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

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
        ScheduleResponse mockResponse = new ScheduleResponse("sch-1", "CREATED");
        when(scheduleService.createSchedule(any(ScheduleRequest.class))).thenReturn(mockResponse);

        ResponseEntity<ScheduleResponse> response = scheduleController.createSchedule(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("sch-1", response.getBody().getScheduleId());
        assertEquals("CREATED", response.getBody().getStatus());
        verify(scheduleService, times(1)).createSchedule(any(ScheduleRequest.class));
    }

    /**
     * Test creating a schedule when service throws exception (error scenario).
     */
    @Test
    void testCreateSchedule_ServiceThrowsException() {
        ScheduleRequest request = new ScheduleRequest();
        when(scheduleService.createSchedule(any(ScheduleRequest.class))).thenThrow(new IllegalArgumentException("Invalid input"));
        assertThrows(IllegalArgumentException.class, () -> scheduleController.createSchedule(request));
        verify(scheduleService, times(1)).createSchedule(any(ScheduleRequest.class));
    }

    /**
     * Test getting schedules when user has schedules.
     */
    @Test
    void testGetSchedules_WithSchedules() {
        List<ScheduleResponse> mockList = Arrays.asList(
                new ScheduleResponse("sch-1", "report-1", "DAILY", "08:00", Collections.singletonList("EMAIL"), "ACTIVE"),
                new ScheduleResponse("sch-2", "report-2", "WEEKLY", "09:00", Collections.singletonList("EMAIL"), "ACTIVE")
        );
        when(scheduleService.getSchedulesForCurrentUser()).thenReturn(mockList);
        ResponseEntity<List<ScheduleResponse>> response = scheduleController.getSchedules();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals("sch-1", response.getBody().get(0).getScheduleId());
        verify(scheduleService, times(1)).getSchedulesForCurrentUser();
    }

    /**
     * Test getting schedules when user has no schedules (edge case).
     */
    @Test
    void testGetSchedules_EmptyList() {
        when(scheduleService.getSchedulesForCurrentUser()).thenReturn(Collections.emptyList());
        ResponseEntity<List<ScheduleResponse>> response = scheduleController.getSchedules();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(scheduleService, times(1)).getSchedulesForCurrentUser();
    }

    /**
     * Test manually triggering a report successfully.
     */
    @Test
    void testTriggerReport_Success() {
        TriggerReportResponse mockResp = new TriggerReportResponse("SUCCESS", new Date());
        when(scheduleService.triggerReport("sch-1")).thenReturn(mockResp);
        ResponseEntity<TriggerReportResponse> response = scheduleController.triggerReport("sch-1");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("SUCCESS", response.getBody().getDeliveryStatus());
        assertNotNull(response.getBody().getTimestamp());
        verify(scheduleService, times(1)).triggerReport("sch-1");
    }

    /**
     * Test manually triggering a report when service throws exception (error scenario).
     */
    @Test
    void testTriggerReport_ServiceThrowsException() {
        when(scheduleService.triggerReport("sch-1")).thenThrow(new RuntimeException("Schedule not found"));
        assertThrows(RuntimeException.class, () -> scheduleController.triggerReport("sch-1"));
        verify(scheduleService, times(1)).triggerReport("sch-1");
    }
}
