package com.example.calculator.model;

import java.math.BigDecimal;

/**
 * Model representing the calculation response payload
 */
public class CalculationResponse {
    private BigDecimal result;
    private String error;

    public CalculationResponse() {}

    public CalculationResponse(BigDecimal result, String error) {
        this.result = result;
        this.error = error;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
