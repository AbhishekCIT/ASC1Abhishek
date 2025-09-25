package com.example.scheduler.model;

import javax.persistence.*;
import java.util.List;

/**
 * Entity representing a report template.
 */
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @Column(nullable = false)
    private String reportType;

    @Column(nullable = false)
    private String format;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    // Getters and Setters
    public Long getReportId() { return reportId; }
    public void setReportId(Long reportId) { this.reportId = reportId; }
    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    public List<Schedule> getSchedules() { return schedules; }
    public void setSchedules(List<Schedule> schedules) { this.schedules = schedules; }
}
