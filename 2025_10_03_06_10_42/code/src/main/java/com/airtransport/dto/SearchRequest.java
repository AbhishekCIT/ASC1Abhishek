package com.airtransport.dto;

import java.util.Map;

/**
 * DTO for search request payload.
 */
public class SearchRequest {
    private String origin;
    private String destination;
    private String date;
    private Map<String, Object> preferences;

    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public Map<String, Object> getPreferences() {
        return preferences;
    }
    public void setPreferences(Map<String, Object> preferences) {
        this.preferences = preferences;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", date='" + date + '\'' +
                ", preferences=" + preferences +
                '}';
    }
}
