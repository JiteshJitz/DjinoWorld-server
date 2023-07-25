package com.example.djinoworld.djinoworld.service.users;

import com.example.djinoworld.djinoworld.model.TravelPartner;
import com.example.djinoworld.djinoworld.repository.users.TravelPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TravelPartnerService {

    @Autowired
    private TravelPartnerRepository travelPartnerRepository;

    public TravelPartner addTravelPartner(TravelPartner travelPartner) {
        travelPartner.setId(UUID.randomUUID().toString());
        return travelPartnerRepository.save(travelPartner);
    }

    public List<TravelPartner> findAllTravelPartners() {
        return travelPartnerRepository.findAll();
    }

    public TravelPartner getTravelPartnerByID(String id){
        return travelPartnerRepository.findById(id).get();
    }

    public String deleteTravelPartner(String id){
        travelPartnerRepository.deleteById(id);
        return "This travel partner request has been deleted " + id;
    }

}
