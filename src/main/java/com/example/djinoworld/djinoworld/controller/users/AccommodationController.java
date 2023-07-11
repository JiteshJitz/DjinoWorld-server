package com.example.djinoworld.djinoworld.controller.users;

import com.example.djinoworld.djinoworld.model.users.Accommodation;
import com.example.djinoworld.djinoworld.service.users.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/accommodation")
public class AccommodationController {

    @Autowired
    private AccommodationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Accommodation createNomad(@RequestBody Accommodation accommodation){
        return service.addAccommodation(accommodation);
    }

    @GetMapping
    public List<Accommodation> getAccommodations(){
        return service.findAllAccommodation();
    }

    @GetMapping("/{accommodationID}")
    public Accommodation getAccommodation(@PathVariable String accommodationID){
        return service.getAccommodationByAccommodationID(accommodationID);
    }

    @DeleteMapping
    public String deleteAccommodation(String accommodationID){
        return service.deleteAccommodation(accommodationID);
    }

    // Test controller
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }

}
