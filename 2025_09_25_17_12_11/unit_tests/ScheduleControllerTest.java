package com.example.scheduler.controller;

import com.example.scheduler.model.*;
import com.example.scheduler.service.NotificationService;
import com.example.scheduler.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Unit tests for ScheduleController.
 */
class ScheduleControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private ScheduleService scheduleService;
    @Mock
    private NotificationService notificationService;
    @InjectMocks
    private ScheduleController scheduleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    @Test
    @DisplayName("Test creating a new schedule - success")
    void testCreateScheduleSuccess() throws Exception {
        ScheduleRequest request = new ScheduleRequest();
        request.setReportId(1L);
        request.setFrequency("DAILY");
        request.setTime("09:00");
        request.setRecipients(Arrays.asList("user1@abc.com"));
        ScheduleResponse response = new ScheduleResponse(101L, "CREATED");
        when(scheduleService.createSchedule(any(ScheduleRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId", is(101)))
                .andExpect(jsonPath("$.status", is("CREATED")));
    }

    @Test
    @DisplayName("Test editing a schedule - success")
    void testEditScheduleSuccess() throws Exception {
        ScheduleRequest request = new ScheduleRequest();
        request.setFrequency("WEEKLY");
        request.setTime("10:00");
        request.setRecipients(Arrays.asList("user2@abc.com"));
        ScheduleResponse response = new ScheduleResponse(101L, "UPDATED");
        when(scheduleService.editSchedule(eq(101L), any(ScheduleRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/schedules/101")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId", is(101)))
                .andExpect(jsonPath("$.status", is("UPDATED")));
    }

    @Test
    @DisplayName("Test deleting a schedule - success")
    void testDeleteScheduleSuccess() throws Exception {
        ScheduleResponse response = new ScheduleResponse(101L, "DELETED");
        when(scheduleService.deleteSchedule(101L)).thenReturn(response);

        mockMvc.perform(delete("/api/schedules/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId", is(101)))
                .andExpect(jsonPath("$.status", is("DELETED")));
    }

    @Test
    @DisplayName("Test listing schedules for a user - success")
    void testListSchedulesSuccess() throws Exception {
        List<ScheduleResponse> responses = Arrays.asList(
                new ScheduleResponse(101L, 1L, "DAILY", "09:00", Arrays.asList("user1@abc.com"), "CREATED"),
                new ScheduleResponse(102L, 2L, "WEEKLY", "10:00", Arrays.asList("user2@abc.com"), "CREATED")
        );
        when(scheduleService.listSchedules(123L)).thenReturn(responses);

        mockMvc.perform(get("/api/schedules?userId=123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].scheduleId", is(101)))
                .andExpect(jsonPath("$[1].scheduleId", is(102)));
    }

    @Test
    @DisplayName("Test trigger schedule - success")
    void testTriggerScheduleSuccess() throws Exception {
        ScheduleResponse response = new ScheduleResponse(101L, "TRIGGERED");
        when(scheduleService.triggerSchedule(101L)).thenReturn(response);

        mockMvc.perform(post("/api/schedules/101/trigger"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId", is(101)))
                .andExpect(jsonPath("$.status", is("TRIGGERED")));
    }

    @Test
    @DisplayName("Test notify user - success")
    void testNotifyUserSuccess() throws Exception {
        NotificationRequest request = new NotificationRequest();
        request.setUserId(123L);
        request.setMessage("Report delivered");
        NotificationResponse response = new NotificationResponse("NOTIFIED");
        when(notificationService.sendNotification(any(NotificationRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/schedules/notifications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("NOTIFIED")));
    }

    @Test
    @DisplayName("Test create schedule - invalid input (empty recipients)")
    void testCreateScheduleInvalidInput() throws Exception {
        // Simulate service throwing exception for invalid input
        ScheduleRequest request = new ScheduleRequest();
        request.setReportId(1L);
        request.setFrequency("DAILY");
        request.setTime("09:00");
        request.setRecipients(Collections.emptyList());
        when(scheduleService.createSchedule(any(ScheduleRequest.class))).thenThrow(new RuntimeException("Invalid email address"));

        mockMvc.perform(post("/api/schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Test edit schedule - not found")
    void testEditScheduleNotFound() throws Exception {
        ScheduleRequest request = new ScheduleRequest();
        request.setFrequency("DAILY");
        request.setTime("09:00");
        request.setRecipients(Arrays.asList("user1@abc.com"));
        when(scheduleService.editSchedule(eq(999L), any(ScheduleRequest.class))).thenThrow(new RuntimeException("Schedule not found"));

        mockMvc.perform(put("/api/schedules/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Test delete schedule - not found")
    void testDeleteScheduleNotFound() throws Exception {
        when(scheduleService.deleteSchedule(999L)).thenThrow(new RuntimeException("Schedule not found"));

        mockMvc.perform(delete("/api/schedules/999"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Test trigger schedule - not found")
    void testTriggerScheduleNotFound() throws Exception {
        when(scheduleService.triggerSchedule(999L)).thenThrow(new RuntimeException("Schedule not found"));

        mockMvc.perform(post("/api/schedules/999/trigger"))
                .andExpect(status().isInternalServerError());
    }
}
