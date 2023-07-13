package com.example.djinoworld.djinoworld.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Accommodation")
@Data
public class Accommodation {
    /*
     * Fields : AccommodationID, firstName, lastName, email, password, location
     * */

    // User ID
    private String ownerID;

    // Unique and not
    // null
    @Id
    private String accommodationID;

    // Unique and not null
    private String residenceName;

    private String address;

    private String accommodationType;




}
