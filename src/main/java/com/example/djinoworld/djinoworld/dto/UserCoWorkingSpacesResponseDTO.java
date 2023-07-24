package com.example.djinoworld.djinoworld.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserCoWorkingSpacesResponseDTO {
    private UserResponseDTO user;
    private List<CoWorkingSpaceResponseDTO> coWorkingSpaces;

    public UserCoWorkingSpacesResponseDTO(UserResponseDTO user, List<CoWorkingSpaceResponseDTO> coWorkingSpaces) {
        this.user = user;
        this.coWorkingSpaces = coWorkingSpaces;
    }
}

