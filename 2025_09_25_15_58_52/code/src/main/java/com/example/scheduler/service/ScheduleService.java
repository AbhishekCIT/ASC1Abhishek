package com.example.scheduler.service;

import com.example.scheduler.dto.*;
import com.example.scheduler.entity.*;
import com.example.scheduler.exception.*;
import com.example.scheduler.repository.ScheduleRepository;
import com.example.scheduler.repository.RecipientRepository;
import com.example.scheduler.util.RecipientValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for scheduling logic, validation, and integration.
 */
@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private RecipientRepository recipientRepository;
    @Autowired
    private RecipientValidatorUtil recipientValidatorUtil;
    @Autowired
    private SchedulerJobManager schedulerJobManager;
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private JavaMailSender javaMailSender;
    // Assume ReportGenerator and ReportDeliveryService are available as beans

    /**
     * Create a new schedule after validation.
     */
    @Transactional
    public ScheduleConfirmationResponse createSchedule(ScheduleCreateRequest request) {
        // Validate recipients
        if (!recipientValidatorUtil.areValidEmails(request.getRecipients())) {
            auditLogService.logFailure("Invalid email address.");
            throw new InvalidEmailException("Invalid email address.");
        }
        // Validate startDate
        if (request.getStartDate().isBefore(LocalDateTime.now())) {
            auditLogService.logFailure("Cannot schedule for past date/time.");
            throw new PastDateException("Cannot schedule for past date/time.");
        }
        // Validate frequency (business rules)
        if (!isValidFrequency(request.getFrequency())) {
            auditLogService.logFailure("Frequency not allowed.");
            throw new FrequencyRuleException("Frequency not allowed.");
        }
        // Persist schedule
        Schedule schedule = new Schedule();
        schedule.setReportId(request.getReportId());
        schedule.setFrequency(request.getFrequency());
        schedule.setDeliveryMethod(request.getDeliveryMethod());
        schedule.setStartDate(request.getStartDate());
        schedule.setNextRun(request.getStartDate());
        schedule.setStatus("SCHEDULED");
        // TODO: Set userId from security context
        schedule = scheduleRepository.save(schedule);
        // Persist recipients
        Set<String> uniqueEmails = new HashSet<>(request.getRecipients());
        List<Recipient> recipients = uniqueEmails.stream().map(email -> {
            Recipient r = new Recipient();
            r.setSchedule(schedule);
            r.setEmail(email);
            return r;
        }).collect(Collectors.toList());
        recipientRepository.saveAll(recipients);
        // Schedule job
        schedulerJobManager.createJob(schedule, recipients);
        auditLogService.logSchedulingAction(schedule.getId(), "CREATE", "SUCCESS");
        // Build response
        ScheduleConfirmationResponse response = new ScheduleConfirmationResponse();
        response.setScheduleId(schedule.getId());
        response.setStatus(schedule.getStatus());
        response.setConfirmation("Report scheduled successfully.");
        return response;
    }

    /**
     * List all schedules for the current user.
     */
    public List<ScheduleListResponse> listSchedules() {
        // TODO: Filter by userId from security context
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleListResponse> responses = new ArrayList<>();
        for (Schedule schedule : schedules) {
            List<Recipient> recipients = recipientRepository.findByScheduleId(schedule.getId());
            ScheduleListResponse resp = new ScheduleListResponse();
            resp.setScheduleId(schedule.getId());
            resp.setReportId(schedule.getReportId());
            resp.setFrequency(schedule.getFrequency());
            resp.setRecipients(recipients.stream().map(Recipient::getEmail).collect(Collectors.toList()));
            resp.setDeliveryMethod(schedule.getDeliveryMethod());
            resp.setNextRun(schedule.getNextRun());
            responses.add(resp);
        }
        return responses;
    }

    /**
     * Remove recipients from a schedule.
     */
    @Transactional
    public ScheduleRecipientsResponse removeRecipients(Long scheduleId, ScheduleRecipientsRequest request) {
        List<Recipient> toRemove = recipientRepository.findByScheduleIdAndEmailIn(scheduleId, request.getRecipients());
        recipientRepository.deleteAll(toRemove);
        List<Recipient> remaining = recipientRepository.findByScheduleId(scheduleId);
        ScheduleRecipientsResponse response = new ScheduleRecipientsResponse();
        response.setScheduleId(scheduleId);
        response.setRecipients(remaining.stream().map(Recipient::getEmail).collect(Collectors.toList()));
        response.setStatus("UPDATED");
        auditLogService.logSchedulingAction(scheduleId, "REMOVE_RECIPIENT", "SUCCESS");
        return response;
    }

    /**
     * Get confirmation for a schedule.
     */
    public ScheduleConfirmationResponse getConfirmation(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        ScheduleConfirmationResponse response = new ScheduleConfirmationResponse();
        response.setScheduleId(schedule.getId());
        response.setConfirmation("Report scheduled successfully.");
        response.setStatus(schedule.getStatus());
        return response;
    }

    /**
     * Validate frequency against business rules.
     */
    private boolean isValidFrequency(String frequency) {
        // Example: Only allow DAILY, WEEKLY, MONTHLY, CUSTOM (not more than once per hour)
        Set<String> allowed = new HashSet<>(Arrays.asList("DAILY", "WEEKLY", "MONTHLY", "CUSTOM"));
        return allowed.contains(frequency);
    }
}
