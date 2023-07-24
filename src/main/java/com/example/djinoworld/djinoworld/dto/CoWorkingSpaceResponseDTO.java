package com.example.djinoworld.djinoworld.dto;

import com.example.djinoworld.djinoworld.model.CoWorkingSpace;
import lombok.Data;

@Data
public class CoWorkingSpaceResponseDTO {
    private String coWorkingSpaceID;
    private String spaceName;
    private String address;
    private String spaceType;

    public CoWorkingSpaceResponseDTO(CoWorkingSpace coWorkingSpace) {
        this.coWorkingSpaceID = coWorkingSpace.getCoWorkingSpaceID();
        this.spaceName = coWorkingSpace.getSpaceName();
        this.address = coWorkingSpace.getAddress();
        this.spaceType = coWorkingSpace.getSpaceType();
    }
}
