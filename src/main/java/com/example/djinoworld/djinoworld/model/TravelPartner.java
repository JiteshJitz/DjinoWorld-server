package com.example.djinoworld.djinoworld.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Document(collection = "TravelPartners")
public class TravelPartner {

    @Id
    private String id;

    @NonNull
    private String nomadID;

    @NonNull
    private String fullName;

    @NonNull
    private TravelDestination travelDestination;

    @NonNull
    private TravelDates travelDates;

    @NonNull
    private TravelPreferences travelPreferences;

    private String travelExpectations;
    private String communication;
    private String budget;
    private String healthAndSafety;

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class TravelDestination {
    private String country;
    private String city;
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class TravelDates {
    private String startDate;
    private String endDate;
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class TravelPreferences {
    private String accommodation;
    private List<String> activities;
}

