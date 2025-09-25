package com.example.scheduler.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity for SCHEDULES table
 */
@Entity
@Table(name = "schedules", uniqueConstraints = {@UniqueConstraint(columnNames = {"report_id", "recipients"})})
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_id", nullable = false)
    private Long reportId;

    @Column(nullable = false)
    private String frequency;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private String recipients; // comma-separated emails

    @Column(name = "next_run")
    private LocalDateTime nextRun;

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getReportId() {
        return reportId;
    }
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }
    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getRecipients() {
        return recipients;
    }
    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }
    public LocalDateTime getNextRun() {
        return nextRun;
    }
    public void setNextRun(LocalDateTime nextRun) {
        this.nextRun = nextRun;
    }
}
