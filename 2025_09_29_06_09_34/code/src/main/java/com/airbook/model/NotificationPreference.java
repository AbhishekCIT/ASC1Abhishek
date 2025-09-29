package com.airbook.model;

import java.util.List;

/**
 * Model for NotificationPreference entity
 */
public class NotificationPreference {
    private String preferenceId;
    private String userId;
    private List<String> channels;

    // Getters and setters
    public String getPreferenceId() { return preferenceId; }
    public void setPreferenceId(String preferenceId) { this.preferenceId = preferenceId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public List<String> getChannels() { return channels; }
    public void setChannels(List<String> channels) { this.channels = channels; }
}
