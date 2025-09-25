package com.example.scheduler.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Entity representing a scheduled report configuration.
 */
@Entity
@Table(name = "schedule", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "report_id", "frequency", "start_date", "end_date"}))
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @Column(nullable = false)
    private String frequency;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String deliveryMethod;

    @Column(nullable = false)
    private String status;

    @ElementCollection
    @CollectionTable(name = "recipient", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "email")
    private List<String> recipients;

    // Getters and Setters
    public Long getScheduleId() { return scheduleId; }
    public void setScheduleId(Long scheduleId) { this.scheduleId = scheduleId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Report getReport() { return report; }
    public void setReport(Report report) { this.report = report; }
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getDeliveryMethod() { return deliveryMethod; }
    public void setDeliveryMethod(String deliveryMethod) { this.deliveryMethod = deliveryMethod; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }
}
