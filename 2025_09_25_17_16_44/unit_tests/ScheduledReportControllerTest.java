package com.example.scheduledreporting.controller;

import com.example.scheduledreporting.model.ScheduledReportRequest;
import com.example.scheduledreporting.model.ScheduledReportResponse;
import com.example.scheduledreporting.service.ScheduledReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * JUnit tests for ScheduledReportController.
 * Covers all REST endpoints, normal and edge cases.
 */
@WebMvcTest(ScheduledReportController.class)
class ScheduledReportControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ScheduledReportService scheduledReportService;
    private UserDetails userDetails;

    @BeforeEach
    void setup() {
        userDetails = User.withUsername("testuser").password("pass").roles("USER").build();
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("Create scheduled report - success")
    void testCreateScheduledReportSuccess() throws Exception {
        ScheduledReportRequest request = buildValidRequest();
        ScheduledReportResponse response = ScheduledReportResponse.builder().id(1).status("SCHEDULED").message("Report scheduled successfully").build();
        when(scheduledReportService.createScheduledReport(eq("testuser"), any(ScheduledReportRequest.class))).thenReturn(response);
        mockMvc.perform(post("/api/scheduled-reports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.status", is("SCHEDULED")))
                .andExpect(jsonPath("$.message", containsString("scheduled successfully")));
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("Create scheduled report - validation error")
    void testCreateScheduledReportValidationError() throws Exception {
        ScheduledReportRequest request = new ScheduledReportRequest(); // invalid, missing fields
        mockMvc.perform(post("/api/scheduled-reports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("Update scheduled report - success")
    void testUpdateScheduledReportSuccess() throws Exception {
        ScheduledReportRequest request = buildValidRequest();
        ScheduledReportResponse response = ScheduledReportResponse.builder().id(2).status("UPDATED").message("Schedule updated").build();
        when(scheduledReportService.updateScheduledReport(eq("testuser"), eq(2), any(ScheduledReportRequest.class))).thenReturn(response);
        mockMvc.perform(put("/api/scheduled-reports/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.status", is("UPDATED")))
                .andExpect(jsonPath("$.message", containsString("updated")));
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("Update scheduled report - not found/unauthorized")
    void testUpdateScheduledReportNotFound() throws Exception {
        ScheduledReportRequest request = buildValidRequest();
        when(scheduledReportService.updateScheduledReport(eq("testuser"), eq(99), any(ScheduledReportRequest.class))).thenThrow(new RuntimeException("Unauthorized to update report"));
        mockMvc.perform(put("/api/scheduled-reports/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("Delete scheduled report - success")
    void testDeleteScheduledReportSuccess() throws Exception {
        ScheduledReportResponse response = ScheduledReportResponse.builder().id(3).status("DELETED").message("Schedule deleted").build();
        when(scheduledReportService.deleteScheduledReport(eq("testuser"), eq(3))).thenReturn(response);
        mockMvc.perform(delete("/api/scheduled-reports/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.status", is("DELETED")))
                .andExpect(jsonPath("$.message", containsString("deleted")));
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("Delete scheduled report - not found/unauthorized")
    void testDeleteScheduledReportNotFound() throws Exception {
        when(scheduledReportService.deleteScheduledReport(eq("testuser"), eq(99))).thenThrow(new RuntimeException("Unauthorized to delete report"));
        mockMvc.perform(delete("/api/scheduled-reports/99"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("Get scheduled reports - success, multiple")
    void testGetScheduledReportsMultiple() throws Exception {
        List<ScheduledReportResponse> responses = Arrays.asList(
                ScheduledReportResponse.builder().id(1).status("SCHEDULED").message("Sales scheduled at 08:00").build(),
                ScheduledReportResponse.builder().id(2).status("SCHEDULED").message("Inventory scheduled at 09:00").build()
        );
        when(scheduledReportService.getScheduledReports(eq("testuser"))).thenReturn(responses);
        mockMvc.perform(get("/api/scheduled-reports"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("Get scheduled reports - empty list")
    void testGetScheduledReportsEmpty() throws Exception {
        when(scheduledReportService.getScheduledReports(eq("testuser"))).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/scheduled-reports"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // Helper method to build a valid request
    private ScheduledReportRequest buildValidRequest() {
        ScheduledReportRequest request = new ScheduledReportRequest();
        ScheduledReportRequest.ScheduleDTO schedule = new ScheduledReportRequest.ScheduleDTO();
        schedule.setFrequency("DAILY");
        schedule.setTime(LocalTime.of(8, 0));
        schedule.setStartDate(LocalDate.now());
        schedule.setEndDate(LocalDate.now().plusDays(7));
        ScheduledReportRequest.DeliveryDTO delivery = new ScheduledReportRequest.DeliveryDTO();
        delivery.setType("EMAIL");
        delivery.setEmail("user@example.com");
        request.setReportType("SALES");
        request.setSchedule(schedule);
        request.setDelivery(delivery);
        return request;
    }
}
