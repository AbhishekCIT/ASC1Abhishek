package com.example.airbooking.entity;

import javax.persistence.*;

/**
 * Entity representing a notification subscription.
 */
@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    private String subscriptionId;
    private String userId;
    private String flightNumber;
    private String email;
    private boolean optedIn;

    public String getSubscriptionId() { return subscriptionId; }
    public void setSubscriptionId(String subscriptionId) { this.subscriptionId = subscriptionId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean isOptedIn() { return optedIn; }
    public void setOptedIn(boolean optedIn) { this.optedIn = optedIn; }
}
