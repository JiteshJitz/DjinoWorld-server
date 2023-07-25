package com.example.djinoworld.djinoworld.dto;

import com.example.djinoworld.djinoworld.model.Event;
import lombok.Data;

import java.util.List;

@Data
public class EventResponseDTO {
    private String eventID;
    private String eventName;
    private String eventPlace;
    private String eventType;
    private String eventDescription;
    private String price;
    private String phoneNumber;
    private List<String> programs;
    private List<String> logistics;
    private String eventDate;
    private String eventTime;

    public static EventResponseDTO fromEvent(Event event) {
        EventResponseDTO eventResponseDTO = new EventResponseDTO();

            eventResponseDTO.setEventID(event.getEventID());
            eventResponseDTO.setEventName(event.getEventName());
            eventResponseDTO.setEventPlace(event.getEventPlace());
            eventResponseDTO.setEventType(event.getEventType());
            eventResponseDTO.setEventDescription(event.getEventDescription());
            eventResponseDTO.setPrice(event.getPrice());
            eventResponseDTO.setPhoneNumber(event.getPhoneNumber());
            eventResponseDTO.setPrograms(event.getPrograms());
            eventResponseDTO.setLogistics(event.getLogistics());
            eventResponseDTO.setEventDate(event.getEventDate());
            eventResponseDTO.setEventTime(event.getEventTime());

            return eventResponseDTO;
        }
}


