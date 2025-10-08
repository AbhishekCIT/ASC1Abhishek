package com.example.calculator.model;

import java.time.LocalDateTime;

/**
 * Model for calculation log (for analytics/audit)
 */
public class CalculationLog {
    private String id;
    private String operation;
    private double num1;
    private double num2;
    private Double result;
    private LocalDateTime timestamp;
    private String errorMessage;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public double getNum1() {
        return num1;
    }
    public void setNum1(double num1) {
        this.num1 = num1;
    }
    public double getNum2() {
        return num2;
    }
    public void setNum2(double num2) {
        this.num2 = num2;
    }
    public Double getResult() {
        return result;
    }
    public void setResult(Double result) {
        this.result = result;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
