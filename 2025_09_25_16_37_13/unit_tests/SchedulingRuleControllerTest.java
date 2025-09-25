package com.example.scheduling.controller;

import com.example.scheduling.model.SchedulingRule;
import com.example.scheduling.service.SchedulingRuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for SchedulingRuleController REST endpoints.
 */
public class SchedulingRuleControllerTest {

    @Mock
    private SchedulingRuleService schedulingRuleService;

    @InjectMocks
    private SchedulingRuleController schedulingRuleController;

    private MockMvc mockMvc;
    private Principal principal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(schedulingRuleController).build();
        principal = () -> "testUser";
    }

    /**
     * Test creating a scheduling rule with valid input.
     */
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testCreateSchedulingRule_Success() throws Exception {
        SchedulingRule rule = new SchedulingRule();
        rule.setId(1L);
        rule.setFrequency("daily");
        rule.setTime("14:00");
        rule.setActive(true);
        rule.setCreatedAt(LocalDateTime.now());
        rule.setUpdatedAt(LocalDateTime.now());
        when(schedulingRuleService.createRule(anyLong(), anyString(), anyString(), anyBoolean(), anyString())).thenReturn(rule);

        String json = "{" +
                "\"reportTypeId\":1," +
                "\"frequency\":\"daily\"," +
                "\"time\":\"14:00\"," +
                "\"isActive\":true" +
                "}";

        mockMvc.perform(post("/api/scheduling-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.status", is("scheduled")));
    }

    /**
     * Test editing a scheduling rule with valid input.
     */
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testEditSchedulingRule_Success() throws Exception {
        SchedulingRule rule = new SchedulingRule();
        rule.setId(2L);
        rule.setFrequency("weekly");
        rule.setTime("09:00");
        rule.setActive(true);
        rule.setCreatedAt(LocalDateTime.now());
        rule.setUpdatedAt(LocalDateTime.now());
        when(schedulingRuleService.editRule(anyLong(), anyString(), anyString(), anyString())).thenReturn(rule);

        String json = "{" +
                "\"frequency\":\"weekly\"," +
                "\"time\":\"09:00\"" +
                "}";

        mockMvc.perform(put("/api/scheduling-rules/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.status", is("updated")));
    }

    /**
     * Test deactivating a scheduling rule.
     */
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testDeactivateSchedulingRule_Success() throws Exception {
        SchedulingRule rule = new SchedulingRule();
        rule.setId(3L);
        rule.setActive(false);
        rule.setCreatedAt(LocalDateTime.now());
        rule.setUpdatedAt(LocalDateTime.now());
        when(schedulingRuleService.deactivateRule(anyLong(), anyString())).thenReturn(rule);

        mockMvc.perform(patch("/api/scheduling-rules/3/deactivate")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.status", is("deactivated")));
    }

    /**
     * Test getting scheduling rules by report type.
     */
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testGetSchedulingRulesByReportType_Success() throws Exception {
        SchedulingRule rule = new SchedulingRule();
        rule.setId(4L);
        rule.setFrequency("monthly");
        rule.setTime("10:00");
        rule.setActive(true);
        rule.setCreatedAt(LocalDateTime.now());
        rule.setUpdatedAt(LocalDateTime.now());
        when(schedulingRuleService.getRulesByReportType(anyLong())).thenReturn(List.of(rule));

        mockMvc.perform(get("/api/scheduling-rules?reportTypeId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(4)))
                .andExpect(jsonPath("$[0].frequency", is("monthly")))
                .andExpect(jsonPath("$[0].time", is("10:00")))
                .andExpect(jsonPath("$[0].active", is(true)));
    }

    /**
     * Test creating a scheduling rule with invalid frequency (edge case).
     */
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testCreateSchedulingRule_InvalidFrequency() throws Exception {
        when(schedulingRuleService.createRule(anyLong(), eq("invalid"), anyString(), anyBoolean(), anyString()))
                .thenThrow(new com.example.scheduling.exception.InvalidFrequencyException("Invalid frequency"));

        String json = "{" +
                "\"reportTypeId\":1," +
                "\"frequency\":\"invalid\"," +
                "\"time\":\"14:00\"," +
                "\"isActive\":true" +
                "}";

        mockMvc.perform(post("/api/scheduling-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .principal(principal))
                .andExpect(status().isInternalServerError());
    }

    /**
     * Test creating a scheduling rule with invalid time format (edge case).
     */
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testCreateSchedulingRule_InvalidTimeFormat() throws Exception {
        when(schedulingRuleService.createRule(anyLong(), anyString(), eq("99:99"), anyBoolean(), anyString()))
                .thenThrow(new com.example.scheduling.exception.InvalidTimeFormatException("Invalid time format"));

        String json = "{" +
                "\"reportTypeId\":1," +
                "\"frequency\":\"daily\"," +
                "\"time\":\"99:99\"," +
                "\"isActive\":true" +
                "}";

        mockMvc.perform(post("/api/scheduling-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .principal(principal))
                .andExpect(status().isInternalServerError());
    }

    /**
     * Test creating a scheduling rule with missing required fields (boundary condition).
     */
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testCreateSchedulingRule_MissingFields() throws Exception {
        String json = "{" +
                "\"frequency\":\"daily\"" +
                "}";

        mockMvc.perform(post("/api/scheduling-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .principal(principal))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test unauthorized access to create scheduling rule (error-handling scenario).
     */
    @Test
    @WithMockUser(roles = {"USER"})
    void testCreateSchedulingRule_Unauthorized() throws Exception {
        String json = "{" +
                "\"reportTypeId\":1," +
                "\"frequency\":\"daily\"," +
                "\"time\":\"14:00\"," +
                "\"isActive\":true" +
                "}";

        mockMvc.perform(post("/api/scheduling-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .principal(principal))
                .andExpect(status().isForbidden());
    }
}
