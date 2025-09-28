package com.example.calculator.dto;

/**
 * DTO for clear response.
 */
public class ClearResponse {
    private String input1;
    private String input2;
    private String result;

    public ClearResponse() {}

    public String getInput1() {
        return input1;
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public String getInput2() {
        return input2;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
