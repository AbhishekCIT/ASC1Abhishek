package com.example.scheduledreporting.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for ScheduledReport entity validation.
 * Covers normal, edge, and error cases for entity fields.
 */
class ScheduledReportTest {
    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Valid ScheduledReport passes validation")
    void testValidScheduledReport() {
        ScheduledReport report = buildValidReport();
        Set<ConstraintViolation<ScheduledReport>> violations = validator.validate(report);
        assertTrue(violations.isEmpty(), "Expected no validation errors for valid report");
    }

    @Test
    @DisplayName("Blank reportType triggers validation error")
    void testBlankReportType() {
        ScheduledReport report = buildValidReport();
        report.setReportType("");
        Set<ConstraintViolation<ScheduledReport>> violations = validator.validate(report);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("reportType")));
    }

    @Test
    @DisplayName("Null scheduleTime triggers validation error")
    void testNullScheduleTime() {
        ScheduledReport report = buildValidReport();
        report.setScheduleTime(null);
        Set<ConstraintViolation<ScheduledReport>> violations = validator.validate(report);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("scheduleTime")));
    }

    @Test
    @DisplayName("Invalid email triggers validation error")
    void testInvalidEmail() {
        ScheduledReport report = buildValidReport();
        report.setDeliveryAddress("not-an-email");
        Set<ConstraintViolation<ScheduledReport>> violations = validator.validate(report);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("deliveryAddress")));
    }

    @Test
    @DisplayName("Blank status triggers validation error")
    void testBlankStatus() {
        ScheduledReport report = buildValidReport();
        report.setStatus("");
        Set<ConstraintViolation<ScheduledReport>> violations = validator.validate(report);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("status")));
    }

    // Helper to build a valid ScheduledReport
    private ScheduledReport buildValidReport() {
        ScheduledReport report = new ScheduledReport();
        User user = new User();
        user.setId(1);
        user.setUsername("user1");
        user.setEmail("user1@example.com");
        user.setRole("USER");
        report.setUser(user);
        report.setReportType("SALES");
        report.setFrequency("DAILY");
        report.setScheduleTime(LocalTime.of(8, 0));
        report.setStartDate(LocalDate.now());
        report.setEndDate(LocalDate.now().plusDays(7));
        report.setDeliveryType("EMAIL");
        report.setDeliveryAddress("user1@example.com");
        report.setStatus("SCHEDULED");
        report.setCreatedAt(LocalDateTime.now());
        report.setUpdatedAt(LocalDateTime.now());
        return report;
    }
}
