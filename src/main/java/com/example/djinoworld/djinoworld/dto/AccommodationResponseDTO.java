package com.example.djinoworld.djinoworld.dto;

import com.example.djinoworld.djinoworld.model.Accommodation;
import lombok.Data;

@Data
public class AccommodationResponseDTO {
    private String residenceID;
    private String residenceName;
    private String address;
    private String accommodationType;

    public AccommodationResponseDTO(Accommodation accommodation) {
        this.residenceID = accommodation.getAccommodationID();
        this.residenceName = accommodation.getResidenceName();
        this.address = accommodation.getAddress();
        this.accommodationType = accommodation.getAccommodationType();
    }
}
