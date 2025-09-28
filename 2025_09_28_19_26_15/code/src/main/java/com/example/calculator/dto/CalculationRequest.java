package com.example.calculator.dto;

/**
 * DTO for incoming calculation requests.
 */
public class CalculationRequest {
    private String num1;
    private String num2;
    private String operation;

    public CalculationRequest() {}

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "CalculationRequest{" +
                "num1='" + num1 + '\'' +
                ", num2='" + num2 + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
