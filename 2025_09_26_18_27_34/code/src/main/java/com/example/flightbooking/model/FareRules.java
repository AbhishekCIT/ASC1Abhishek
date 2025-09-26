package com.example.flightbooking.model;

/**
 * Model for airline fare rules.
 */
public class FareRules {
    private double modificationFee;
    private double cancellationFee;

    // Getters and Setters
    public double getModificationFee() { return modificationFee; }
    public void setModificationFee(double modificationFee) { this.modificationFee = modificationFee; }
    public double getCancellationFee() { return cancellationFee; }
    public void setCancellationFee(double cancellationFee) { this.cancellationFee = cancellationFee; }
}
