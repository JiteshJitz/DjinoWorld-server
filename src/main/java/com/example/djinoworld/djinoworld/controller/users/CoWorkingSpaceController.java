package com.example.djinoworld.djinoworld.controller.users;

import com.example.djinoworld.djinoworld.dto.UserCoWorkingSpacesResponseDTO;
import com.example.djinoworld.djinoworld.model.CoWorkingSpace;
import com.example.djinoworld.djinoworld.service.users.CoWorkingSpaceService;
import com.example.djinoworld.djinoworld.service.users.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/space")
public class CoWorkingSpaceController {

    @Autowired
    private CoWorkingSpaceService service;

    @Autowired
    private UserManager userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CoWorkingSpace createNomad(@RequestBody CoWorkingSpace coWorkingSpace){
        return service.addCoWorkingSpace(coWorkingSpace);
    }

    @GetMapping
    public List<CoWorkingSpace> getCoWorkingSpaces(){
        return service.findAllCoWorkingSpaces();
    }

    @GetMapping("/{coWorkingSpaceID}")
    public CoWorkingSpace getCoWorkingSpace(@PathVariable String coWorkingSpaceID){
        return service.getCoWorkingSpaceByID(coWorkingSpaceID);
    }

    @DeleteMapping
    public String deleteCoWorkingSpace(String coWorkingSpaceID){
        return service.deleteCoWorkingSpace(coWorkingSpaceID);
    }

    @GetMapping("/{userId}/coworkingspace")
    public UserCoWorkingSpacesResponseDTO getUserAndCoWorkingSpaces(@PathVariable String userId) {
        return userService.getUserAndCoWorkingSpacesInfo(userId);
    }

    @GetMapping("/filter")
    public List<CoWorkingSpace> filterCoWorkingSpaces(@RequestParam(required = false) String address,
                                                      @RequestParam(required = false) String spaceType) {
        return service.filterCoWorkingSpaces(address, spaceType);
    }


}
