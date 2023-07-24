package com.example.djinoworld.djinoworld.model;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Document(collection = "Accommodation")
@Data
@Getter
@Setter
public class Accommodation {
    /*
       Type: Hostels / Homestay / Short term, long term rentals for digital nomads / Hotels with includes Wi-Fi,  Homes and Others
     * Fields : Property Name, Property Type, Location, Description, Accommodation Features, Pricing, Photos, Amenities, Nearby Facilities, Reviews/Testimonials, Booking Process, Additional Services, House Rules, Contact Information
     * */

    // User ID unique
    @NonNull
    private String ownerID;

    // Unique and not
    // null
    @Id
    private String accommodationID;

    // Unique
    @NonNull
    private String residenceName;

    @NonNull
    private String address;

    @NonNull
    private String accommodationType;

    @NonNull
    private String description;

    private List<String> amenities;

    @NonNull
    private Float price;

    private List<String> facilities;

    //private HashMap<Integer,String> reviews;

    @NonNull
    private String phoneNumber;


}
