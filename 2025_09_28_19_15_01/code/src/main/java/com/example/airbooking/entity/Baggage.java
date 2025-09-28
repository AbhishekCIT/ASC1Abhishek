package com.example.airbooking.entity;

import javax.persistence.*;

/**
 * Entity representing baggage for a check-in.
 */
@Entity
@Table(name = "baggage")
public class Baggage {
    @Id
    private String baggageId;
    private String checkInId;
    private int quantity;
    private double weight;

    public String getBaggageId() { return baggageId; }
    public void setBaggageId(String baggageId) { this.baggageId = baggageId; }
    public String getCheckInId() { return checkInId; }
    public void setCheckInId(String checkInId) { this.checkInId = checkInId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
}
