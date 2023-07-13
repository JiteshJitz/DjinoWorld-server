package com.example.djinoworld.djinoworld.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CoWorkingSpace")
@Data
public class CoWorkingSpace {

    /*
     * Fields : coWorkingSpaceID, firstName, lastName, email, password, location, spaceID
     * */

    @Id
    private String coWorkingSpaceID;

    private String ownerId;

    @NonNull
    private String spaceName;

    private String address;

}
