package com.example.djinoworld.djinoworld.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Events")
@Data
@Getter
@Setter
public class Event {

    /*
     * Fields : EventCoordinatorID, firstName, lastName, email, password, location
     * */


    private String eventCoordinatorID;

    // Make it unique or find a way to make it id
    @Id
    private String eventID;

    private String eventName;

    private String eventPlace;

}
