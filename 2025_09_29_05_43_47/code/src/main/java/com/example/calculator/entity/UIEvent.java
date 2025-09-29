package com.example.calculator.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing UI event logs (analytics, accessibility)
 */
@Entity
@Table(name = "ui_event")
public class UIEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String eventType;

    @Column
    private String deviceType;

    @Column
    private String browser;

    @Column
    private String accessibilityStatus;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public UIEvent() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getAccessibilityStatus() {
        return accessibilityStatus;
    }

    public void setAccessibilityStatus(String accessibilityStatus) {
        this.accessibilityStatus = accessibilityStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
