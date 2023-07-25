package com.example.djinoworld.djinoworld.controller;

import com.example.djinoworld.djinoworld.model.TravelPartner;
import com.example.djinoworld.djinoworld.service.users.TravelPartnerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/travelPartner")
public class TravelPartnerController {

    @Autowired
    private TravelPartnerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TravelPartner createTravelPartner(@RequestBody TravelPartner travelPartner){
        return service.addTravelPartner(travelPartner);
    }

    @GetMapping
    public List<TravelPartner> getAllTravelPartners(){
        return service.findAllTravelPartners();
    }

    @GetMapping("/{id}")
    public TravelPartner getTravelPartner(@PathVariable String id){
        return service.getTravelPartnerByID(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTravelPartner(@PathVariable String id){
        return service.deleteTravelPartner(id);
    }

    @GetMapping("/search")
    public List<TravelPartner> searchTravelPartners(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String budget,
            @RequestParam(required = false) String accommodation) {

        return service.searchTravelPartners(country, startDate, endDate, budget, accommodation);
    }

}
