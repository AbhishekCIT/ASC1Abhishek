package com.example.airbooking.model;

import javax.persistence.*;

@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    private String subscriptionId;
    private String userId;
    private String flightNumber;
    private String contact;
    private String type;

    // Getters and Setters
    public String getSubscriptionId() { return subscriptionId; }
    public void setSubscriptionId(String subscriptionId) { this.subscriptionId = subscriptionId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}