package com.example.djinoworld.djinoworld.model;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Events")
@Data
@Getter
@Setter
public class Event {

    /*
     * Fields : Event Type and description, Event Date and Time, Venue, Program
     * Catering and Food Preferences, Guest Speakers or Performers, Event Branding and Marketing, Registration or Ticketing Requirements (Buy),
     * On-site Logistics - parking, Communication and Reporting(Email)
     * */

    @NonNull
    @Indexed(unique = true)
    private String eventCoordinatorID;

    // Make it unique or find a way to make it id
    @Id
    private String eventID;

    @NonNull
    private String eventName;

    @NonNull
    private String eventPlace;

    @NonNull
    private String eventType;

    @NonNull
    private String eventDescription;

    @NonNull
    private String price;

    @NonNull
    private String phoneNumber;

    private List<String> programs;

    //parking and food
    private List<String> logistics;

    private String eventDate;

    private String eventTime;


}
