package com.example.scheduler.entity;

import javax.persistence.*;

/**
 * Entity representing a report template.
 */
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reportName;

    @Column(nullable = false)
    private String format;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReportName() { return reportName; }
    public void setReportName(String reportName) { this.reportName = reportName; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
}
