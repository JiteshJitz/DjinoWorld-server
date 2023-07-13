package com.example.djinoworld.djinoworld.service.users;

import com.example.djinoworld.djinoworld.model.Event;
import com.example.djinoworld.djinoworld.repository.users.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

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

    //   Individual put

    public String deleteEvent(String eventID){
        eventRepository.deleteById(eventID);
        return "This user has been deleted " + eventID;
    }


}
