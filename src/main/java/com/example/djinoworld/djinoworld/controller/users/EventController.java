package com.example.djinoworld.djinoworld.controller.users;

import com.example.djinoworld.djinoworld.dto.EventCoordinatorEventsInfo;
import com.example.djinoworld.djinoworld.model.Event;
import com.example.djinoworld.djinoworld.service.users.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event event){
        return service.addEvent(event);
    }

    @GetMapping
    public List<Event> getEvents(){
        return service.findAllEvents();
    }

    @GetMapping("/{eventID}")
    public Event getEvent(@PathVariable String eventID){
        return service.getEventByEventID(eventID);
    }

    @DeleteMapping
    public String deleteEvent(String eventID){
        return service.deleteEvent(eventID);
    }

    @GetMapping("/info/{coordinatorID}")
    public EventCoordinatorEventsInfo getEventCoordinatorAndEventsInfo(@PathVariable String coordinatorID) {
        return service.getEventCoordinatorAndEventsInfo(coordinatorID);
    }

    @GetMapping("/filter")
    public List<Event> filterEvents(@RequestParam(required = false) String eventCity,
                                    @RequestParam(required = false) String eventCountry,
                                    @RequestParam(required = false) String eventType,
                                    @RequestParam(required = false) String fromDate,
                                    @RequestParam(required = false) String toDate) {
        return service.filterEvents(eventCity, eventCountry, eventType, fromDate, toDate);
    }

}
