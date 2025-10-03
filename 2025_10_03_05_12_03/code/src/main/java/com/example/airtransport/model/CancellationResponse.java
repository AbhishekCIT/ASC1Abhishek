package com.example.airtransport.model;

/**
 * Model representing cancellation confirmation response.
 */
public class CancellationResponse {
    private String status;
    private double refundAmount;
    private String policy;
    private String confirmation;

    public CancellationResponse() {}
    public CancellationResponse(String status, double refundAmount, String policy, String confirmation) {
        this.status = status;
        this.refundAmount = refundAmount;
        this.policy = policy;
        this.confirmation = confirmation;
    }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getRefundAmount() { return refundAmount; }
    public void setRefundAmount(double refundAmount) { this.refundAmount = refundAmount; }
    public String getPolicy() { return policy; }
    public void setPolicy(String policy) { this.policy = policy; }
    public String getConfirmation() { return confirmation; }
    public void setConfirmation(String confirmation) { this.confirmation = confirmation; }
}
