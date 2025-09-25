package com.example.scheduler.controller;

import com.example.scheduler.dto.*;
import com.example.scheduler.exception.*;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
 * Unit tests for ScheduleController.
 * Covers all endpoints, normal and edge cases, and error handling.
 */
@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    @Autowired
    private ObjectMapper objectMapper;

    private ScheduleCreateRequest createRequest;
    private ScheduleConfirmationResponse confirmationResponse;
    private ScheduleRecipientsRequest recipientsRequest;
    private ScheduleRecipientsResponse recipientsResponse;
    private List<ScheduleListResponse> scheduleList;

    @BeforeEach
    void setUp() {
        createRequest = new ScheduleCreateRequest();
        createRequest.setReportId(1L);
        createRequest.setFrequency("DAILY");
        createRequest.setRecipients(Arrays.asList("user@example.com"));
        createRequest.setDeliveryMethod("EMAIL");
        createRequest.setStartDate("2024-07-01T09:00:00");

        confirmationResponse = new ScheduleConfirmationResponse();
        confirmationResponse.setScheduleId(100L);
        confirmationResponse.setStatus("SCHEDULED");
        confirmationResponse.setConfirmation("Report scheduled successfully.");

        recipientsRequest = new ScheduleRecipientsRequest();
        recipientsRequest.setRecipients(Collections.singletonList("user@example.com"));

        recipientsResponse = new ScheduleRecipientsResponse();
        recipientsResponse.setScheduleId(100L);
        recipientsResponse.setRecipients(Collections.singletonList("other@example.com"));
        recipientsResponse.setStatus("UPDATED");

        ScheduleListResponse listResponse = new ScheduleListResponse();
        listResponse.setScheduleId(100L);
        listResponse.setReportId(1L);
        listResponse.setFrequency("DAILY");
        listResponse.setRecipients(Arrays.asList("user@example.com"));
        listResponse.setDeliveryMethod("EMAIL");
        listResponse.setNextRun("2024-07-02T09:00:00");
        scheduleList = Collections.singletonList(listResponse);
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Test createSchedule - success")
    void testCreateScheduleSuccess() throws Exception {
        // Test normal scenario for creating a schedule
        when(scheduleService.createSchedule(any(ScheduleCreateRequest.class))).thenReturn(confirmationResponse);
        mockMvc.perform(post("/api/schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(100L))
                .andExpect(jsonPath("$.status").value("SCHEDULED"))
                .andExpect(jsonPath("$.confirmation").value("Report scheduled successfully."));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Test createSchedule - invalid email")
    void testCreateScheduleInvalidEmail() throws Exception {
        // Test error scenario for invalid email
        when(scheduleService.createSchedule(any(ScheduleCreateRequest.class)))
                .thenThrow(new InvalidEmailException("Invalid email address."));
        mockMvc.perform(post("/api/schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Test createSchedule - past date")
    void testCreateSchedulePastDate() throws Exception {
        // Test error scenario for past date
        when(scheduleService.createSchedule(any(ScheduleCreateRequest.class)))
                .thenThrow(new PastDateException("Cannot schedule for past date/time."));
        mockMvc.perform(post("/api/schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Test createSchedule - frequency rule violation")
    void testCreateScheduleFrequencyRuleViolation() throws Exception {
        // Test error scenario for frequency rule violation
        when(scheduleService.createSchedule(any(ScheduleCreateRequest.class)))
                .thenThrow(new FrequencyRuleException("Frequency not allowed."));
        mockMvc.perform(post("/api/schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Test listSchedules - success")
    void testListSchedulesSuccess() throws Exception {
        // Test normal scenario for listing schedules
        when(scheduleService.listSchedules()).thenReturn(scheduleList);
        mockMvc.perform(get("/api/schedules"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].scheduleId").value(100L))
                .andExpect(jsonPath("$[0].frequency").value("DAILY"));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Test removeRecipients - success")
    void testRemoveRecipientsSuccess() throws Exception {
        // Test normal scenario for removing recipients
        when(scheduleService.removeRecipients(eq(100L), any(ScheduleRecipientsRequest.class))).thenReturn(recipientsResponse);
        mockMvc.perform(put("/api/schedules/100/recipients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipientsRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(100L))
                .andExpect(jsonPath("$.recipients[0]").value("other@example.com"))
                .andExpect(jsonPath("$.status").value("UPDATED"));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Test removeRecipients - invalid email")
    void testRemoveRecipientsInvalidEmail() throws Exception {
        // Test error scenario for invalid email during recipient removal
        when(scheduleService.removeRecipients(eq(100L), any(ScheduleRecipientsRequest.class)))
                .thenThrow(new InvalidEmailException("Invalid email address."));
        mockMvc.perform(put("/api/schedules/100/recipients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipientsRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Test getConfirmation - success")
    void testGetConfirmationSuccess() throws Exception {
        // Test normal scenario for getting confirmation
        when(scheduleService.getConfirmation(100L)).thenReturn(confirmationResponse);
        mockMvc.perform(get("/api/schedules/100/confirmation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(100L))
                .andExpect(jsonPath("$.confirmation").value("Report scheduled successfully."));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Test getConfirmation - not found")
    void testGetConfirmationNotFound() throws Exception {
        // Test error scenario for non-existent schedule
        when(scheduleService.getConfirmation(999L)).thenThrow(new RuntimeException("Schedule not found"));
        mockMvc.perform(get("/api/schedules/999/confirmation"))
                .andExpect(status().isInternalServerError());
    }
}
