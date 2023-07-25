package com.example.djinoworld.djinoworld.dto;

import lombok.Data;

import java.util.List;

@Data
public class EventCoordinatorEventsInfo {
    private UserResponseDTO user;
    private List<EventResponseDTO> events;

    public EventCoordinatorEventsInfo(UserResponseDTO user, List<EventResponseDTO> events) {
        this.user = user;
        this.events = events;
    }
}
