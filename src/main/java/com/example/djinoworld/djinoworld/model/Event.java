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

    @Id
    private String eventCoordinatorID;

    private String eventID;

    private String eventName;

    private String eventPlace;

}
