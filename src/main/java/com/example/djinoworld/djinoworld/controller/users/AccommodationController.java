package com.example.djinoworld.djinoworld.controller.users;

import com.example.djinoworld.djinoworld.model.Accommodation;
import com.example.djinoworld.djinoworld.model.User;
import com.example.djinoworld.djinoworld.repository.users.UserRepository;
import com.example.djinoworld.djinoworld.service.users.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/management/accommodation")
public class AccommodationController {

    @Autowired
    private AccommodationService service;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Accommodation createAccommodation(@RequestBody Accommodation accommodation){
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



}
