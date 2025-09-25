package com.example.scheduling.service;

import com.example.scheduling.entity.ScheduleEntity;
import com.example.scheduling.exception.*;
import com.example.scheduling.model.ScheduleRequest;
import com.example.scheduling.model.ScheduleResponse;
import com.example.scheduling.repository.ScheduleRepository;
import com.example.scheduling.util.TimeFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

/**
 * Service class for handling scheduling logic and validations.
 */
@Service
public class ScheduleService {
    private static final List<String> ALLOWED_FREQUENCIES = Arrays.asList("daily", "weekly", "monthly");
    private static final List<String> SUPPORTED_REPORT_TYPES = Arrays.asList("PDF", "Excel");

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ReportService reportService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    /**
     * Create a new schedule after validating all parameters.
     */
    @Transactional
    public ScheduleResponse createSchedule(ScheduleRequest request) {
        validateScheduleRequest(request);
        ScheduleEntity entity = new ScheduleEntity();
        entity.setFrequency(request.getFrequency());
        entity.setTime(request.getTime());
        entity.setReportType(request.getReportType());
        entity.setRecipients(String.join(",", request.getRecipients()));
        entity.setCreatedBy(request.getCreatedBy());
        entity.setUpdatedAt(LocalDateTime.now());
        scheduleRepository.save(entity);
        notificationService.sendConfirmationEmail(entity);
        return new ScheduleResponse(entity.getId(), "created");
    }

    /**
     * Edit an existing schedule.
     */
    @Transactional
    public ScheduleResponse editSchedule(Long id, ScheduleRequest request) {
        validateScheduleRequest(request);
        ScheduleEntity entity = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found"));
        entity.setFrequency(request.getFrequency());
        entity.setTime(request.getTime());
        entity.setReportType(request.getReportType());
        entity.setRecipients(String.join(",", request.getRecipients()));
        entity.setUpdatedAt(LocalDateTime.now());
        scheduleRepository.save(entity);
        notificationService.sendConfirmationEmail(entity);
        return new ScheduleResponse(entity.getId(), "updated");
    }

    /**
     * Delete a schedule.
     */
    @Transactional
    public ScheduleResponse deleteSchedule(Long id) {
        ScheduleEntity entity = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found"));
        scheduleRepository.delete(entity);
        return new ScheduleResponse(id, "deleted");
    }

    /**
     * Get details of a schedule.
     */
    public Optional<ScheduleResponse> getSchedule(Long id) {
        return scheduleRepository.findById(id)
                .map(entity -> new ScheduleResponse(
                        entity.getId(),
                        entity.getFrequency(),
                        entity.getTime(),
                        entity.getReportType(),
                        Arrays.asList(entity.getRecipients().split(","))));
    }

    /**
     * Validate the scheduling parameters.
     */
    private void validateScheduleRequest(ScheduleRequest request) {
        if (!ALLOWED_FREQUENCIES.contains(request.getFrequency())) {
            throw new InvalidFrequencyException("Invalid frequency");
        }
        if (!TimeFormatUtil.isValid24HourFormat(request.getTime())) {
            throw new InvalidTimeFormatException("Invalid time format");
        }
        if (request.getRecipients() == null || request.getRecipients().isEmpty()) {
            throw new NoRecipientException("No recipient selected");
        }
        if (!SUPPORTED_REPORT_TYPES.contains(request.getReportType())) {
            throw new UnsupportedReportTypeException("Unsupported report type");
        }
    }
}
