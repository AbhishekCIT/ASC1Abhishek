package com.example.airbooking.model;

/**
 * PassengerDetail holds details for each passenger in a booking.
 */
public class PassengerDetail {
    private String name;
    private String gender;
    private int age;
    private String passportNumber;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }
}
