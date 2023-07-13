package com.example.djinoworld.djinoworld.model.users;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "nomad")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Nomad {

    @Id
    private String nomadID;


    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String location;


    @NonNull
    private String email;


    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    private String occupation;

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

}
