package com.example.calculator.model;

/**
 * Model class for calculation response.
 */
public class CalculationResponse {
    private Double result;

    public CalculationResponse() {}

    public CalculationResponse(Double result) {
        this.result = result;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
