package com.example.scheduler.controller;

import com.example.scheduler.model.ScheduleRequest;
import com.example.scheduler.model.ScheduleResponse;
import com.example.scheduler.model.TriggerReportResponse;
import com.example.scheduler.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for ScheduleController
 */
@WebMvcTest(ScheduleController.class)
public class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    @Autowired
    private ObjectMapper objectMapper;

    private ScheduleRequest validRequest;
    private ScheduleResponse validResponse;
    private TriggerReportResponse triggerResponse;

    @BeforeEach
    void setUp() {
        validRequest = new ScheduleRequest(1L, "daily", "08:00", Arrays.asList("user@example.com"));
        validResponse = new ScheduleResponse(123L, 1L, "daily", "08:00", Arrays.asList("user@example.com"), LocalDateTime.now().plusDays(1));
        triggerResponse = new TriggerReportResponse("success", LocalDateTime.now());
    }

    @Test
    @DisplayName("Test createSchedule - success")
    void testCreateScheduleSuccess() throws Exception {
        // Test normal schedule creation
        when(scheduleService.createSchedule(any(ScheduleRequest.class))).thenReturn(validResponse);
        mockMvc.perform(post("/api/schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(123L))
                .andExpect(jsonPath("$.frequency").value("daily"));
    }

    @Test
    @DisplayName("Test createSchedule - invalid input")
    void testCreateScheduleInvalidInput() throws Exception {
        // Test invalid frequency
        ScheduleRequest invalidRequest = new ScheduleRequest(1L, "invalid", "08:00", Arrays.asList("user@example.com"));
        when(scheduleService.createSchedule(any(ScheduleRequest.class))).thenThrow(new RuntimeException("Invalid frequency value"));
        mockMvc.perform(post("/api/schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Invalid frequency value")));
    }

    @Test
    @DisplayName("Test editSchedule - success")
    void testEditScheduleSuccess() throws Exception {
        // Test normal schedule edit
        when(scheduleService.editSchedule(eq(123L), any(ScheduleRequest.class))).thenReturn(validResponse);
        mockMvc.perform(put("/api/schedules/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(123L));
    }

    @Test
    @DisplayName("Test editSchedule - not found")
    void testEditScheduleNotFound() throws Exception {
        // Test editing non-existent schedule
        when(scheduleService.editSchedule(eq(999L), any(ScheduleRequest.class))).thenThrow(new RuntimeException("Schedule not found"));
        mockMvc.perform(put("/api/schedules/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Schedule not found")));
    }

    @Test
    @DisplayName("Test deleteSchedule - success")
    void testDeleteScheduleSuccess() throws Exception {
        // Test normal schedule deletion
        ScheduleResponse deleteResponse = new ScheduleResponse(123L, "deleted");
        when(scheduleService.deleteSchedule(123L)).thenReturn(deleteResponse);
        mockMvc.perform(delete("/api/schedules/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("deleted"));
    }

    @Test
    @DisplayName("Test deleteSchedule - not found")
    void testDeleteScheduleNotFound() throws Exception {
        // Test deleting non-existent schedule
        when(scheduleService.deleteSchedule(999L)).thenThrow(new RuntimeException("Schedule not found"));
        mockMvc.perform(delete("/api/schedules/999"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Schedule not found")));
    }

    @Test
    @DisplayName("Test viewSchedules - empty list")
    void testViewSchedulesEmpty() throws Exception {
        // Test viewing schedules when none exist
        when(scheduleService.viewSchedules()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/schedules"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DisplayName("Test viewSchedules - multiple schedules")
    void testViewSchedulesMultiple() throws Exception {
        // Test viewing multiple schedules
        List<ScheduleResponse> responses = Arrays.asList(validResponse,
                new ScheduleResponse(124L, 2L, "weekly", "09:00", Arrays.asList("user2@example.com"), LocalDateTime.now().plusWeeks(1)));
        when(scheduleService.viewSchedules()).thenReturn(responses);
        mockMvc.perform(get("/api/schedules"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].scheduleId").value(123L))
                .andExpect(jsonPath("$[1].scheduleId").value(124L));
    }

    @Test
    @DisplayName("Test triggerReport - success")
    void testTriggerReportSuccess() throws Exception {
        // Test successful report trigger
        when(scheduleService.triggerReport(123L)).thenReturn(triggerResponse);
        mockMvc.perform(post("/api/schedules/123/trigger"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deliveryStatus").value("success"));
    }

    @Test
    @DisplayName("Test triggerReport - schedule not found")
    void testTriggerReportScheduleNotFound() throws Exception {
        // Test triggering report for non-existent schedule
        when(scheduleService.triggerReport(999L)).thenThrow(new RuntimeException("Schedule not found"));
        mockMvc.perform(post("/api/schedules/999/trigger"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Schedule not found")));
    }

    @Test
    @DisplayName("Test triggerReport - report generation failure")
    void testTriggerReportGenerationFailure() throws Exception {
        // Test report generation failure
        when(scheduleService.triggerReport(123L)).thenThrow(new RuntimeException("Report generation failed"));
        mockMvc.perform(post("/api/schedules/123/trigger"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Report generation failed")));
    }

    @Test
    @DisplayName("Test triggerReport - delivery failure")
    void testTriggerReportDeliveryFailure() throws Exception {
        // Test report delivery failure
        when(scheduleService.triggerReport(123L)).thenThrow(new RuntimeException("Report delivery failed"));
        mockMvc.perform(post("/api/schedules/123/trigger"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Report delivery failed")));
    }
}
