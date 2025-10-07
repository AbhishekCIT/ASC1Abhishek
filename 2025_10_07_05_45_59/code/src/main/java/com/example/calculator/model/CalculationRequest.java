package com.example.calculator.model;

/**
 * Model class representing the request for a calculation operation.
 */
public class CalculationRequest {
    private double number1;
    private double number2;
    private String operation;

    public CalculationRequest() {}

    public double getNumber1() {
        return number1;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
