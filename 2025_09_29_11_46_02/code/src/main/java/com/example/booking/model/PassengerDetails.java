package com.example.booking.model;

/**
 * Model representing passenger details.
 */
public class PassengerDetails {
    private String name;
    private String passport;

    public PassengerDetails() {}
    public PassengerDetails(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassport() { return passport; }
    public void setPassport(String passport) { this.passport = passport; }

    @Override
    public String toString() {
        return "PassengerDetails{" +
                "name='" + name + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}
