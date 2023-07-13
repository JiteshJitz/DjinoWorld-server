package com.example.djinoworld.djinoworld.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Document(collection = "CoWorkingSpace")
@Data
public class CoWorkingSpace {

    /*
     * Type: Co-working space / Coffee shops/ Office spaces/ Other
     * Fields : Space Name, Location, Space Description, Membership Options, Amenities, Workspace Options, Community and Networking, Additional Services,
     * Pricing, Photos, Operating Hours, Security Measures, Contact Information, reviews
     * */

    @Id
    private String coWorkingSpaceID;

    // Unique
    @NonNull
    private String ownerId;

    @NonNull
    private String spaceName;

    @NonNull
    private String address;

    @NonNull
    private String spaceType;

    @NonNull
    private String phoneNumber;

    private String description;

    private HashMap<String,String> membershipOptions;

    private List<String> amenities;

    private List<String> reviews;

}
