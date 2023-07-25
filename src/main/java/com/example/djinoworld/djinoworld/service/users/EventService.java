package com.example.djinoworld.djinoworld.service.users;

import com.example.djinoworld.djinoworld.dto.EventCoordinatorEventsInfo;
import com.example.djinoworld.djinoworld.dto.EventResponseDTO;
import com.example.djinoworld.djinoworld.dto.UserResponseDTO;
import com.example.djinoworld.djinoworld.model.Event;
import com.example.djinoworld.djinoworld.model.User;
import com.example.djinoworld.djinoworld.repository.users.EventRepository;
import com.example.djinoworld.djinoworld.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    // Crud create, read, update, delete

    public Event addEvent(Event event) {
        event.setEventID(UUID.randomUUID().toString());
        return eventRepository.save(event);
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventByEventID(String eventID){
        return eventRepository.findById(eventID).get();
    }




    public List<Event> getEventsByCoordinatorID(String coordinatorID){
        return eventRepository.findByEventCoordinatorID(coordinatorID);
    }

    public EventCoordinatorEventsInfo getEventCoordinatorAndEventsInfo(String coordinatorID) {
        User user = userRepository.findById(coordinatorID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Event> events = getEventsByCoordinatorID(coordinatorID);
        List<EventResponseDTO> eventResponseDTOS = events.stream()
                .map(EventResponseDTO::fromEvent)
                .collect(Collectors.toList());

        UserResponseDTO userResponseDTO = UserResponseDTO.fromUser(user);

        return new EventCoordinatorEventsInfo(userResponseDTO, eventResponseDTOS);
    }

    //   Individual put

    public String deleteEvent(String eventID){
        eventRepository.deleteById(eventID);
        return "This user has been deleted " + eventID;
    }




}
