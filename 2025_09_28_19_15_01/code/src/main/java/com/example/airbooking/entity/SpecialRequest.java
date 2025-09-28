package com.example.airbooking.entity;

import javax.persistence.*;

/**
 * Entity representing a special request for a check-in.
 */
@Entity
@Table(name = "specialrequest")
public class SpecialRequest {
    @Id
    private String requestId;
    private String checkInId;
    private String type;
    private String details;

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
    public String getCheckInId() { return checkInId; }
    public void setCheckInId(String checkInId) { this.checkInId = checkInId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
