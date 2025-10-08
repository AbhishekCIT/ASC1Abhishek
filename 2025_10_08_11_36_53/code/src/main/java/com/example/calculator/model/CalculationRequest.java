package com.example.calculator.model;

/**
 * Model for calculation API request
 */
public class CalculationRequest {
    private Object num1;
    private Object num2;
    private String operation;

    public Object getNum1() {
        return num1;
    }
    public void setNum1(Object num1) {
        this.num1 = num1;
    }
    public Object getNum2() {
        return num2;
    }
    public void setNum2(Object num2) {
        this.num2 = num2;
    }
    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
}
