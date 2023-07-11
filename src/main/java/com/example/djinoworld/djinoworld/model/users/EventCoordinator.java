package com.example.djinoworld.djinoworld.model.users;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "eventCoordinator")
@Data
public class EventCoordinator {

    /*
    * Fields : EventCoordinatorID, firstName, lastName, email, password, location
    * */

    @Id
    private String EventCoordinatorID;
    private String firstName;
    private String lastName;
    private String location;
    private String email;
    private String password;

    public EventCoordinator() {
    }

    public EventCoordinator(String eventCoordinatorID, String firstName, String lastName, String location, String email, String password) {
        EventCoordinatorID = eventCoordinatorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.email = email;
        this.password = password;
    }

    public String getEventCoordinatorID() {
        return EventCoordinatorID;
    }

    public void setEventCoordinatorID(String eventCoordinatorID) {
        EventCoordinatorID = eventCoordinatorID;
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
        return "EventCoordinator{" +
                "EventCoordinatorID='" + EventCoordinatorID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
