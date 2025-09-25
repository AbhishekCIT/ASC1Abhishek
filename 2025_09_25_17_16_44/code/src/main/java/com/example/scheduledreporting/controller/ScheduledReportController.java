package com.example.scheduledreporting.controller;

import com.example.scheduledreporting.model.ScheduledReportRequest;
import com.example.scheduledreporting.model.ScheduledReportResponse;
import com.example.scheduledreporting.service.ScheduledReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing scheduled reports.
 */
@RestController
@RequestMapping("/api/scheduled-reports")
@RequiredArgsConstructor
public class ScheduledReportController {
    private final ScheduledReportService scheduledReportService;

    /**
     * Create a new scheduled report.
     */
    @PostMapping
    public ResponseEntity<ScheduledReportResponse> createScheduledReport(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody ScheduledReportRequest request) {
        ScheduledReportResponse response = scheduledReportService.createScheduledReport(userDetails.getUsername(), request);
        return ResponseEntity.ok(response);
    }

    /**
     * Update an existing scheduled report.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduledReportResponse> updateScheduledReport(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id,
            @Valid @RequestBody ScheduledReportRequest request) {
        ScheduledReportResponse response = scheduledReportService.updateScheduledReport(userDetails.getUsername(), id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a scheduled report.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduledReportResponse> deleteScheduledReport(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id) {
        ScheduledReportResponse response = scheduledReportService.deleteScheduledReport(userDetails.getUsername(), id);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all scheduled reports for the authenticated user.
     */
    @GetMapping
    public ResponseEntity<List<ScheduledReportResponse>> getScheduledReports(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<ScheduledReportResponse> responses = scheduledReportService.getScheduledReports(userDetails.getUsername());
        return ResponseEntity.ok(responses);
    }
}
