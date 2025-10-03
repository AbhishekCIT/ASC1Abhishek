package com.example.airtransport.model;

/**
 * Model representing price range for filtering flights.
 */
public class PriceRange {
    private double min;
    private double max;

    // Getters and setters
    public double getMin() { return min; }
    public void setMin(double min) { this.min = min; }
    public double getMax() { return max; }
    public void setMax(double max) { this.max = max; }
}
