package com.example.calculator.dto;

/**
 * DTO for outgoing calculation responses.
 */
public class CalculationResponse {
    private Double result;
    private String error;

    public CalculationResponse() {}

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
