package com.example.djinoworld.djinoworld.controller.users;

import com.example.djinoworld.djinoworld.model.CoWorkingSpace;
import com.example.djinoworld.djinoworld.service.users.CoWorkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/space")
public class CoWorkingSpaceController {

    @Autowired
    private CoWorkingSpaceService service;

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


}
