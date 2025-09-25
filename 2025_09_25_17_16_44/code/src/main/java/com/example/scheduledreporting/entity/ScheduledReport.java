package com.example.scheduledreporting.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

/**
 * Entity representing a scheduled report for a user.
 */
@Entity
@Table(name = "scheduled_report", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "report_type", "schedule_time"})
})
@Getter
@Setter
public class ScheduledReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank
    @Column(name = "report_type", nullable = false)
    private String reportType;

    @NotBlank
    @Column(name = "frequency", nullable = false)
    private String frequency; // DAILY, WEEKLY, MONTHLY, etc.

    @NotNull
    @Column(name = "schedule_time", nullable = false)
    private LocalTime scheduleTime;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotBlank
    @Column(name = "delivery_type", nullable = false)
    private String deliveryType; // EMAIL, NOTIFICATION, etc.

    @Column(name = "delivery_address")
    @Email(message = "Invalid email address")
    private String deliveryAddress;

    @NotBlank
    @Column(name = "status", nullable = false)
    private String status; // SCHEDULED, PAUSED, DELETED, etc.

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
