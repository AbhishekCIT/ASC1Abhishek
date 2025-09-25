package com.example.scheduledreporting.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for ScheduledReportRequest and nested DTO validation.
 * Covers normal, edge, and error cases for request fields.
 */
class ScheduledReportRequestTest {
    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Valid ScheduledReportRequest passes validation")
    void testValidRequest() {
        ScheduledReportRequest req = buildValidRequest();
        Set<ConstraintViolation<ScheduledReportRequest>> violations = validator.validate(req);
        assertTrue(violations.isEmpty(), "Expected no validation errors for valid request");
    }

    @Test
    @DisplayName("Blank reportType triggers validation error")
    void testBlankReportType() {
        ScheduledReportRequest req = buildValidRequest();
        req.setReportType("");
        Set<ConstraintViolation<ScheduledReportRequest>> violations = validator.validate(req);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("reportType")));
    }

    @Test
    @DisplayName("Null schedule triggers validation error")
    void testNullSchedule() {
        ScheduledReportRequest req = buildValidRequest();
        req.setSchedule(null);
        Set<ConstraintViolation<ScheduledReportRequest>> violations = validator.validate(req);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("schedule")));
    }

    @Test
    @DisplayName("Blank frequency in schedule triggers validation error")
    void testBlankFrequency() {
        ScheduledReportRequest req = buildValidRequest();
        req.getSchedule().setFrequency("");
        Set<ConstraintViolation<ScheduledReportRequest.ScheduleDTO>> violations = validator.validate(req.getSchedule());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("frequency")));
    }

    @Test
    @DisplayName("Null delivery triggers validation error")
    void testNullDelivery() {
        ScheduledReportRequest req = buildValidRequest();
        req.setDelivery(null);
        Set<ConstraintViolation<ScheduledReportRequest>> violations = validator.validate(req);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("delivery")));
    }

    @Test
    @DisplayName("Blank delivery type triggers validation error")
    void testBlankDeliveryType() {
        ScheduledReportRequest req = buildValidRequest();
        req.getDelivery().setType("");
        Set<ConstraintViolation<ScheduledReportRequest.DeliveryDTO>> violations = validator.validate(req.getDelivery());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("type")));
    }

    @Test
    @DisplayName("Invalid email in delivery triggers validation error")
    void testInvalidEmail() {
        ScheduledReportRequest req = buildValidRequest();
        req.getDelivery().setEmail("not-an-email");
        Set<ConstraintViolation<ScheduledReportRequest.DeliveryDTO>> violations = validator.validate(req.getDelivery());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    // Helper to build a valid ScheduledReportRequest
    private ScheduledReportRequest buildValidRequest() {
        ScheduledReportRequest req = new ScheduledReportRequest();
        req.setReportType("SALES");
        ScheduledReportRequest.ScheduleDTO schedule = new ScheduledReportRequest.ScheduleDTO();
        schedule.setFrequency("DAILY");
        schedule.setTime(LocalTime.of(8, 0));
        schedule.setStartDate(LocalDate.now());
        schedule.setEndDate(LocalDate.now().plusDays(7));
        req.setSchedule(schedule);
        ScheduledReportRequest.DeliveryDTO delivery = new ScheduledReportRequest.DeliveryDTO();
        delivery.setType("EMAIL");
        delivery.setEmail("user@example.com");
        req.setDelivery(delivery);
        return req;
    }
}
