package com.example.airbooking.model;

/**
 * Model for passenger information
 */
public class PassengerInfo {
    private String name;
    private int age;
    private String passportNumber;
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }
}
