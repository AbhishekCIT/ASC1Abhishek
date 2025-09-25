package com.example.scheduler.controller;

import com.example.scheduler.dto.*;
import com.example.scheduler.entity.*;
import com.example.scheduler.exception.*;
import com.example.scheduler.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST API controller for scheduling operations.
 * Handles endpoints for creating schedules, listing schedules, removing recipients, and confirming scheduling.
 */
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    /**
     * Create a new report schedule.
     * @param request Schedule creation request DTO
     * @return Confirmation response DTO
     */
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ScheduleConfirmationResponse> createSchedule(@RequestBody ScheduleCreateRequest request) {
        ScheduleConfirmationResponse response = scheduleService.createSchedule(request);
        return ResponseEntity.ok(response);
    }

    /**
     * List all schedules for the authenticated user.
     * @return List of schedule DTOs
     */
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ScheduleListResponse>> listSchedules() {
        List<ScheduleListResponse> schedules = scheduleService.listSchedules();
        return ResponseEntity.ok(schedules);
    }

    /**
     * Remove recipients from a schedule.
     * @param scheduleId Schedule ID
     * @param request Recipient removal request DTO
     * @return Updated recipients response DTO
     */
    @PutMapping("/{scheduleId}/recipients")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ScheduleRecipientsResponse> removeRecipients(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRecipientsRequest request) {
        ScheduleRecipientsResponse response = scheduleService.removeRecipients(scheduleId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Get confirmation for a scheduled report.
     * @param scheduleId Schedule ID
     * @return Confirmation response DTO
     */
    @GetMapping("/{scheduleId}/confirmation")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ScheduleConfirmationResponse> getConfirmation(@PathVariable Long scheduleId) {
        ScheduleConfirmationResponse response = scheduleService.getConfirmation(scheduleId);
        return ResponseEntity.ok(response);
    }
}
