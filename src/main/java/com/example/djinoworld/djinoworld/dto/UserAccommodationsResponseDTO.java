package com.example.djinoworld.djinoworld.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserAccommodationsResponseDTO {
    private UserResponseDTO user;
    private List<AccommodationResponseDTO> accommodations;

    public UserAccommodationsResponseDTO(UserResponseDTO user, List<AccommodationResponseDTO> accommodations) {
        this.user = user;
        this.accommodations = accommodations;
    }
}
