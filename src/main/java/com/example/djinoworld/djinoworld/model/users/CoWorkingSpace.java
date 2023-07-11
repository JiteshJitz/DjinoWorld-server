package com.example.djinoworld.djinoworld.model.users;

import org.springframework.data.annotation.Id;

public class CoWorkingSpace {

    /*
     * Fields : coWorkingSpaceID, firstName, lastName, email, password, location, spaceID
     * */

    @Id
    private String coWorkingSpaceID;
    private String firstName;
    private String lastName;
    private String location;
    private String email;
    private String password;

    public CoWorkingSpace() {
    }

    public CoWorkingSpace(String coWorkingSpaceID, String firstName, String lastName, String location, String email, String password) {
        this.coWorkingSpaceID = coWorkingSpaceID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.email = email;
        this.password = password;
    }

    public String getCoWorkingSpaceID() {
        return coWorkingSpaceID;
    }

    public void setCoWorkingSpaceID(String coWorkingSpaceID) {
        this.coWorkingSpaceID = coWorkingSpaceID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CoWorkingSpace{" +
                "coWorkingSpaceID='" + coWorkingSpaceID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
