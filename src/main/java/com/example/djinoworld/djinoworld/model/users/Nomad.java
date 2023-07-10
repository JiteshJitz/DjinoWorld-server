package com.example.djinoworld.djinoworld.model.users;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nomad")
@Data
public class Nomad {

    @Id
    private String nomadID;
    private String firstName;
    private String lastName;
    private String location;
    private String email;

    public Nomad() {
    }

    public Nomad(String nomadID, String firstName, String lastName, String location, String email) {
        this.nomadID = nomadID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.email = email;
    }

    public String getNomadID() {
        return nomadID;
    }

    public void setNomadID(String nomadID) {
        this.nomadID = nomadID;
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

    @Override
    public String toString() {
        return "Nomad{" +
                "nomadID='" + nomadID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
