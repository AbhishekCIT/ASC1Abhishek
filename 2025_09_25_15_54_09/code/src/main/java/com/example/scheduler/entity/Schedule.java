package com.example.scheduler.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Entity representing a scheduled report.
 */
@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String scheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String reportId;
    private String frequency;
    private String time;
    private String status;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "schedule_delivery_methods", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "delivery_method")
    private List<String> deliveryMethods;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "schedule_recipients", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "recipient")
    private List<String> recipients;

    // Getters and Setters
    public String getScheduleId() { return scheduleId; }
    public void setScheduleId(String scheduleId) { this.scheduleId = scheduleId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getReportId() { return reportId; }
    public void setReportId(String reportId) { this.reportId = reportId; }
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<String> getDeliveryMethods() { return deliveryMethods; }
    public void setDeliveryMethods(List<String> deliveryMethods) { this.deliveryMethods = deliveryMethods; }
    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }
}
