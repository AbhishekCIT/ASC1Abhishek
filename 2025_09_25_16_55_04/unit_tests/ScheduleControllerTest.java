package com.example.scheduling.controller;

import com.example.scheduling.model.ScheduleRequest;
import com.example.scheduling.model.ScheduleResponse;
import com.example.scheduling.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

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
    @DisplayName("createSchedule: should return OK and created status for valid request")
    void testCreateSchedule_Success() {
        ScheduleRequest request = new ScheduleRequest();
        request.setFrequency("daily");
        request.setTime("14:00");
        request.setReportType("PDF");
        request.setRecipients(Arrays.asList("user1@example.com"));
        request.setCreatedBy(1L);
        ScheduleResponse response = new ScheduleResponse(1L, "created");
        when(scheduleService.createSchedule(any(ScheduleRequest.class))).thenReturn(response);

        ResponseEntity<ScheduleResponse> result = scheduleController.createSchedule(request);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("created", result.getBody().getStatus());
        verify(scheduleService, times(1)).createSchedule(any(ScheduleRequest.class));
    }

    /**
     * Test editing a schedule with valid input.
     */
    @Test
    @DisplayName("editSchedule: should return OK and updated status for valid request")
    void testEditSchedule_Success() {
        ScheduleRequest request = new ScheduleRequest();
        request.setFrequency("weekly");
        request.setTime("09:00");
        request.setReportType("Excel");
        request.setRecipients(Arrays.asList("user2@example.com"));
        request.setCreatedBy(2L);
        ScheduleResponse response = new ScheduleResponse(2L, "updated");
        when(scheduleService.editSchedule(eq(2L), any(ScheduleRequest.class))).thenReturn(response);

        ResponseEntity<ScheduleResponse> result = scheduleController.editSchedule(2L, request);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("updated", result.getBody().getStatus());
        verify(scheduleService, times(1)).editSchedule(eq(2L), any(ScheduleRequest.class));
    }

    /**
     * Test deleting a schedule with valid ID.
     */
    @Test
    @DisplayName("deleteSchedule: should return OK and deleted status for valid ID")
    void testDeleteSchedule_Success() {
        ScheduleResponse response = new ScheduleResponse(3L, "deleted");
        when(scheduleService.deleteSchedule(3L)).thenReturn(response);

        ResponseEntity<ScheduleResponse> result = scheduleController.deleteSchedule(3L);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("deleted", result.getBody().getStatus());
        verify(scheduleService, times(1)).deleteSchedule(3L);
    }

    /**
     * Test getting a schedule that exists.
     */
    @Test
    @DisplayName("getSchedule: should return OK and schedule details for existing ID")
    void testGetSchedule_Found() {
        ScheduleResponse response = new ScheduleResponse(4L, "daily", "14:00", "PDF", Arrays.asList("user1@example.com"));
        when(scheduleService.getSchedule(4L)).thenReturn(Optional.of(response));

        ResponseEntity<ScheduleResponse> result = scheduleController.getSchedule(4L);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(4L, result.getBody().getId());
        assertEquals("daily", result.getBody().getFrequency());
        verify(scheduleService, times(1)).getSchedule(4L);
    }

    /**
     * Test getting a schedule that does not exist.
     */
    @Test
    @DisplayName("getSchedule: should return 404 for non-existing ID")
    void testGetSchedule_NotFound() {
        when(scheduleService.getSchedule(99L)).thenReturn(Optional.empty());

        ResponseEntity<ScheduleResponse> result = scheduleController.getSchedule(99L);
        assertEquals(404, result.getStatusCodeValue());
        assertNull(result.getBody());
        verify(scheduleService, times(1)).getSchedule(99L);
    }

    /**
     * Test creating a schedule with missing recipients (edge case).
     */
    @Test
    @DisplayName("createSchedule: should propagate exception for missing recipients")
    void testCreateSchedule_MissingRecipients() {
        ScheduleRequest request = new ScheduleRequest();
        request.setFrequency("daily");
        request.setTime("14:00");
        request.setReportType("PDF");
        request.setRecipients(Collections.emptyList());
        request.setCreatedBy(1L);
        when(scheduleService.createSchedule(any(ScheduleRequest.class))).thenThrow(new RuntimeException("No recipient selected"));

        Exception exception = assertThrows(RuntimeException.class, () -> scheduleController.createSchedule(request));
        assertTrue(exception.getMessage().contains("No recipient selected"));
        verify(scheduleService, times(1)).createSchedule(any(ScheduleRequest.class));
    }

    /**
     * Test editing a schedule with invalid frequency (edge case).
     */
    @Test
    @DisplayName("editSchedule: should propagate exception for invalid frequency")
    void testEditSchedule_InvalidFrequency() {
        ScheduleRequest request = new ScheduleRequest();
        request.setFrequency("yearly"); // Invalid
        request.setTime("09:00");
        request.setReportType("PDF");
        request.setRecipients(Arrays.asList("user@example.com"));
        request.setCreatedBy(1L);
        when(scheduleService.editSchedule(eq(5L), any(ScheduleRequest.class))).thenThrow(new RuntimeException("Invalid frequency"));

        Exception exception = assertThrows(RuntimeException.class, () -> scheduleController.editSchedule(5L, request));
        assertTrue(exception.getMessage().contains("Invalid frequency"));
        verify(scheduleService, times(1)).editSchedule(eq(5L), any(ScheduleRequest.class));
    }
}
