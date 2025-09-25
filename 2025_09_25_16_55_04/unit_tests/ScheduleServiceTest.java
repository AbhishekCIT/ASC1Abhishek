package com.example.scheduling.service;

import com.example.scheduling.entity.ScheduleEntity;
import com.example.scheduling.exception.*;
import com.example.scheduling.model.ScheduleRequest;
import com.example.scheduling.model.ScheduleResponse;
import com.example.scheduling.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
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
    private ReportService reportService;
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

    /**
     * Test creating a schedule with valid parameters.
     */
    @Test
    @DisplayName("createSchedule: should save entity and return created status for valid request")
    void testCreateSchedule_Success() {
        ScheduleRequest request = buildValidRequest();
        ScheduleEntity savedEntity = new ScheduleEntity();
        savedEntity.setId(1L);
        savedEntity.setFrequency(request.getFrequency());
        savedEntity.setTime(request.getTime());
        savedEntity.setReportType(request.getReportType());
        savedEntity.setRecipients(String.join(",", request.getRecipients()));
        savedEntity.setCreatedBy(request.getCreatedBy());
        savedEntity.setUpdatedAt(LocalDateTime.now());
        when(scheduleRepository.save(any(ScheduleEntity.class))).thenAnswer(invocation -> {
            ScheduleEntity e = invocation.getArgument(0);
            e.setId(1L);
            return e;
        });

        ScheduleResponse response = scheduleService.createSchedule(request);
        assertEquals(1L, response.getId());
        assertEquals("created", response.getStatus());
        verify(scheduleRepository, times(1)).save(any(ScheduleEntity.class));
        verify(notificationService, times(1)).sendConfirmationEmail(any(ScheduleEntity.class));
    }

    /**
     * Test creating a schedule with invalid frequency (edge case).
     */
    @Test
    @DisplayName("createSchedule: should throw InvalidFrequencyException for invalid frequency")
    void testCreateSchedule_InvalidFrequency() {
        ScheduleRequest request = buildValidRequest();
        request.setFrequency("yearly");
        assertThrows(InvalidFrequencyException.class, () -> scheduleService.createSchedule(request));
        verify(scheduleRepository, never()).save(any());
    }

    /**
     * Test creating a schedule with invalid time format (edge case).
     */
    @Test
    @DisplayName("createSchedule: should throw InvalidTimeFormatException for invalid time")
    void testCreateSchedule_InvalidTime() {
        ScheduleRequest request = buildValidRequest();
        request.setTime("25:00");
        assertThrows(InvalidTimeFormatException.class, () -> scheduleService.createSchedule(request));
        verify(scheduleRepository, never()).save(any());
    }

    /**
     * Test creating a schedule with no recipients (edge case).
     */
    @Test
    @DisplayName("createSchedule: should throw NoRecipientException for empty recipients")
    void testCreateSchedule_NoRecipients() {
        ScheduleRequest request = buildValidRequest();
        request.setRecipients(Collections.emptyList());
        assertThrows(NoRecipientException.class, () -> scheduleService.createSchedule(request));
        verify(scheduleRepository, never()).save(any());
    }

    /**
     * Test creating a schedule with unsupported report type (edge case).
     */
    @Test
    @DisplayName("createSchedule: should throw UnsupportedReportTypeException for unsupported report type")
    void testCreateSchedule_UnsupportedReportType() {
        ScheduleRequest request = buildValidRequest();
        request.setReportType("CSV");
        assertThrows(UnsupportedReportTypeException.class, () -> scheduleService.createSchedule(request));
        verify(scheduleRepository, never()).save(any());
    }

    /**
     * Test editing a schedule with valid parameters.
     */
    @Test
    @DisplayName("editSchedule: should update entity and return updated status for valid request")
    void testEditSchedule_Success() {
        ScheduleRequest request = buildValidRequest();
        ScheduleEntity entity = new ScheduleEntity();
        entity.setId(2L);
        entity.setFrequency("weekly");
        entity.setTime("09:00");
        entity.setReportType("Excel");
        entity.setRecipients("user2@example.com");
        entity.setCreatedBy(2L);
        entity.setUpdatedAt(LocalDateTime.now());
        when(scheduleRepository.findById(2L)).thenReturn(Optional.of(entity));
        when(scheduleRepository.save(any(ScheduleEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ScheduleResponse response = scheduleService.editSchedule(2L, request);
        assertEquals(2L, response.getId());
        assertEquals("updated", response.getStatus());
        verify(scheduleRepository, times(1)).findById(2L);
        verify(scheduleRepository, times(1)).save(any(ScheduleEntity.class));
        verify(notificationService, times(1)).sendConfirmationEmail(any(ScheduleEntity.class));
    }

    /**
     * Test editing a schedule that does not exist (error case).
     */
    @Test
    @DisplayName("editSchedule: should throw ScheduleNotFoundException for missing schedule")
    void testEditSchedule_NotFound() {
        ScheduleRequest request = buildValidRequest();
        when(scheduleRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ScheduleNotFoundException.class, () -> scheduleService.editSchedule(99L, request));
        verify(scheduleRepository, times(1)).findById(99L);
        verify(scheduleRepository, never()).save(any());
    }

    /**
     * Test deleting a schedule with valid ID.
     */
    @Test
    @DisplayName("deleteSchedule: should delete entity and return deleted status for valid ID")
    void testDeleteSchedule_Success() {
        ScheduleEntity entity = new ScheduleEntity();
        entity.setId(3L);
        when(scheduleRepository.findById(3L)).thenReturn(Optional.of(entity));
        doNothing().when(scheduleRepository).delete(entity);

        ScheduleResponse response = scheduleService.deleteSchedule(3L);
        assertEquals(3L, response.getId());
        assertEquals("deleted", response.getStatus());
        verify(scheduleRepository, times(1)).findById(3L);
        verify(scheduleRepository, times(1)).delete(entity);
    }

    /**
     * Test deleting a schedule that does not exist (error case).
     */
    @Test
    @DisplayName("deleteSchedule: should throw ScheduleNotFoundException for missing schedule")
    void testDeleteSchedule_NotFound() {
        when(scheduleRepository.findById(88L)).thenReturn(Optional.empty());
        assertThrows(ScheduleNotFoundException.class, () -> scheduleService.deleteSchedule(88L));
        verify(scheduleRepository, times(1)).findById(88L);
        verify(scheduleRepository, never()).delete(any());
    }

    /**
     * Test getting a schedule that exists.
     */
    @Test
    @DisplayName("getSchedule: should return Optional with ScheduleResponse for existing ID")
    void testGetSchedule_Found() {
        ScheduleEntity entity = new ScheduleEntity();
        entity.setId(4L);
        entity.setFrequency("daily");
        entity.setTime("14:00");
        entity.setReportType("PDF");
        entity.setRecipients("user1@example.com,user2@example.com");
        when(scheduleRepository.findById(4L)).thenReturn(Optional.of(entity));

        Optional<ScheduleResponse> result = scheduleService.getSchedule(4L);
        assertTrue(result.isPresent());
        assertEquals(4L, result.get().getId());
        assertEquals("daily", result.get().getFrequency());
        assertEquals(2, result.get().getRecipients().size());
        verify(scheduleRepository, times(1)).findById(4L);
    }

    /**
     * Test getting a schedule that does not exist.
     */
    @Test
    @DisplayName("getSchedule: should return Optional.empty for non-existing ID")
    void testGetSchedule_NotFound() {
        when(scheduleRepository.findById(77L)).thenReturn(Optional.empty());
        Optional<ScheduleResponse> result = scheduleService.getSchedule(77L);
        assertFalse(result.isPresent());
        verify(scheduleRepository, times(1)).findById(77L);
    }

    // Helper method to build a valid ScheduleRequest
    private ScheduleRequest buildValidRequest() {
        ScheduleRequest request = new ScheduleRequest();
        request.setFrequency("daily");
        request.setTime("14:00");
        request.setReportType("PDF");
        request.setRecipients(Arrays.asList("user1@example.com", "user2@example.com"));
        request.setCreatedBy(1L);
        return request;
    }
}
