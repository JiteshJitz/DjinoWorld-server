package com.example.djinoworld.djinoworld.controller.users;


import com.example.djinoworld.djinoworld.dto.NomadUserInfo;
import com.example.djinoworld.djinoworld.model.User;
import com.example.djinoworld.djinoworld.model.users.Nomad;
import com.example.djinoworld.djinoworld.service.users.NomadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nomad")
public class NomadController {

    @Autowired
    private NomadService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Nomad createNomad(@RequestBody Nomad nomad){
        return service.addNomad(nomad);
    }

    @GetMapping
    public List<Nomad> getNomads(){
        return service.findAllNomads();
    }

    @GetMapping("/{nomadId}")
    public Nomad getNomad(@PathVariable String nomadId){
        return service.getNomadByNomadID(nomadId);
    }

    @DeleteMapping
    public String deleteNomad(String nomadId){
        return service.deleteNomad(nomadId);
    }

    @GetMapping("/info/{nomadId}")
    public NomadUserInfo getNomadAndUser(@PathVariable String nomadId){
        return service.getNomadAndUserByNomadID(nomadId);
    }

}
