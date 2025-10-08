package com.example.calculator.model;

/**
 * Model for calculation API response
 */
public class CalculationResponse {
    private Double result;
    private String error;

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
