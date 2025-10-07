package com.example.calculator.model;

/**
 * Model class representing the response for a calculation operation.
 */
public class CalculationResponse {
    private Double result;
    private String error;

    public CalculationResponse() {}

    public CalculationResponse(Double result, String error) {
        this.result = result;
        this.error = error;
    }

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
