package com.example.djinoworld.djinoworld.controller.users;

import com.example.djinoworld.djinoworld.model.users.EventCoordinator;
import com.example.djinoworld.djinoworld.service.users.EventCoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/eventcooordinator")
public class EventCoordinatorController {

    @Autowired
    private EventCoordinatorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventCoordinator createEventCoordinator(@RequestBody EventCoordinator eventCoordinator){
        return service.addCoordinator(eventCoordinator);
    }

    @GetMapping
    public List<EventCoordinator> getEventCoordinators(){
        return service.findAllCoordinators();
    }

    @GetMapping("/{eventCoordinatorID}")
    public EventCoordinator getEventCoordinator(@PathVariable String eventCoordinatorID){
        return service.getCoordinatorByCoordinatorID(eventCoordinatorID);
    }

    @DeleteMapping
    public String deleteEventCoordinator(String eventCoordinatorID){
        return service.deleteCoordinator(eventCoordinatorID);
    }

    // Test controller
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }
}
