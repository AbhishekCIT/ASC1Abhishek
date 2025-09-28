package com.example.calculator.dto;

import java.util.Objects;

/**
 * DTO for calculation history entry.
 */
public class CalculationHistoryItem {
    private double input1;
    private String operation;
    private double input2;
    private double result;

    public CalculationHistoryItem() {}

    public double getInput1() {
        return input1;
    }

    public void setInput1(double input1) {
        this.input1 = input1;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getInput2() {
        return input2;
    }

    public void setInput2(double input2) {
        this.input2 = input2;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculationHistoryItem that = (CalculationHistoryItem) o;
        return Double.compare(that.input1, input1) == 0 &&
                Double.compare(that.input2, input2) == 0 &&
                Double.compare(that.result, result) == 0 &&
                Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input1, operation, input2, result);
    }

    @Override
    public String toString() {
        return "CalculationHistoryItem{" +
                "input1=" + input1 +
                ", operation='" + operation + '\'' +
                ", input2=" + input2 +
                ", result=" + result +
                '}';
    }
}
