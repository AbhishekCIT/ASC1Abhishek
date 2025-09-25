package com.example.scheduledreporting.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Request DTO for creating or updating a scheduled report.
 */
@Getter
@Setter
public class ScheduledReportRequest {
    @NotBlank
    private String reportType;
    @NotNull
    private ScheduleDTO schedule;
    @NotNull
    private DeliveryDTO delivery;

    @Getter
    @Setter
    public static class ScheduleDTO {
        @NotBlank
        private String frequency;
        @NotNull
        private LocalTime time;
        @NotNull
        private LocalDate startDate;
        @NotNull
        private LocalDate endDate;
    }

    @Getter
    @Setter
    public static class DeliveryDTO {
        @NotBlank
        private String type; // EMAIL, NOTIFICATION, etc.
        @Email(message = "Invalid email address")
        private String email;
    }
}
