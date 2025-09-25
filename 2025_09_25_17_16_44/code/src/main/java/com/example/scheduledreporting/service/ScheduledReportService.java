package com.example.scheduledreporting.service;

import com.example.scheduledreporting.entity.ScheduledReport;
import com.example.scheduledreporting.entity.User;
import com.example.scheduledreporting.model.ScheduledReportRequest;
import com.example.scheduledreporting.model.ScheduledReportResponse;
import com.example.scheduledreporting.repository.ScheduledReportRepository;
import com.example.scheduledreporting.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for managing scheduled reports and business logic.
 */
@Service
@Validated
@RequiredArgsConstructor
public class ScheduledReportService {
    private final ScheduledReportRepository scheduledReportRepository;
    private final UserRepository userRepository;
    private final SchedulerService schedulerService;
    private final DeliveryService deliveryService;
    private final AuditLogService auditLogService;
    private final UserService userService;

    /**
     * Create a new scheduled report.
     */
    @Transactional
    public ScheduledReportResponse createScheduledReport(String username, ScheduledReportRequest request) {
        User user = userService.getAuthorizedUser(username);
        // Check for schedule overlap
        if (scheduledReportRepository.existsByUserAndReportTypeAndScheduleTime(user, request.getReportType(), request.getSchedule().getTime())) {
            throw new IllegalArgumentException("Schedule overlaps with existing");
        }
        ScheduledReport report = new ScheduledReport();
        report.setUser(user);
        report.setReportType(request.getReportType());
        report.setFrequency(request.getSchedule().getFrequency());
        report.setScheduleTime(request.getSchedule().getTime());
        report.setStartDate(request.getSchedule().getStartDate());
        report.setEndDate(request.getSchedule().getEndDate());
        report.setDeliveryType(request.getDelivery().getType());
        report.setDeliveryAddress(request.getDelivery().getEmail());
        report.setStatus("SCHEDULED");
        report.setCreatedAt(LocalDateTime.now());
        report.setUpdatedAt(LocalDateTime.now());
        ScheduledReport saved = scheduledReportRepository.save(report);
        schedulerService.scheduleReport(saved);
        auditLogService.logAction(user, "CREATE", saved);
        return ScheduledReportResponse.builder()
                .id(saved.getId())
                .status(saved.getStatus())
                .message("Report scheduled successfully")
                .build();
    }

    /**
     * Update an existing scheduled report.
     */
    @Transactional
    public ScheduledReportResponse updateScheduledReport(String username, Integer id, ScheduledReportRequest request) {
        User user = userService.getAuthorizedUser(username);
        ScheduledReport report = scheduledReportRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new AccessDeniedException("Unauthorized to update report"));
        // Check for schedule overlap
        if (scheduledReportRepository.existsByUserAndReportTypeAndScheduleTime(user, request.getReportType(), request.getSchedule().getTime()) &&
                !report.getScheduleTime().equals(request.getSchedule().getTime())) {
            throw new IllegalArgumentException("Schedule overlaps with existing");
        }
        report.setFrequency(request.getSchedule().getFrequency());
        report.setScheduleTime(request.getSchedule().getTime());
        report.setStartDate(request.getSchedule().getStartDate());
        report.setEndDate(request.getSchedule().getEndDate());
        report.setDeliveryType(request.getDelivery().getType());
        report.setDeliveryAddress(request.getDelivery().getEmail());
        report.setUpdatedAt(LocalDateTime.now());
        ScheduledReport saved = scheduledReportRepository.save(report);
        schedulerService.rescheduleReport(saved);
        auditLogService.logAction(user, "UPDATE", saved);
        return ScheduledReportResponse.builder()
                .id(saved.getId())
                .status("UPDATED")
                .message("Schedule updated")
                .build();
    }

    /**
     * Delete a scheduled report.
     */
    @Transactional
    public ScheduledReportResponse deleteScheduledReport(String username, Integer id) {
        User user = userService.getAuthorizedUser(username);
        ScheduledReport report = scheduledReportRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new AccessDeniedException("Unauthorized to delete report"));
        scheduledReportRepository.delete(report);
        schedulerService.unscheduleReport(report);
        auditLogService.logAction(user, "DELETE", report);
        return ScheduledReportResponse.builder()
                .id(report.getId())
                .status("DELETED")
                .message("Schedule deleted")
                .build();
    }

    /**
     * Get all scheduled reports for the user.
     */
    public List<ScheduledReportResponse> getScheduledReports(String username) {
        User user = userService.getAuthorizedUser(username);
        return scheduledReportRepository.findByUser(user).stream()
                .map(report -> ScheduledReportResponse.builder()
                        .id(report.getId())
                        .status(report.getStatus())
                        .message(report.getReportType() + " scheduled at " + report.getScheduleTime())
                        .build())
                .collect(Collectors.toList());
    }
}
