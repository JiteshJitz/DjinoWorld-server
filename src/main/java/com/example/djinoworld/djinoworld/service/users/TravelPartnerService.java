package com.example.djinoworld.djinoworld.service.users;

import com.example.djinoworld.djinoworld.model.TravelPartner;
import com.example.djinoworld.djinoworld.repository.users.TravelPartnerRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TravelPartnerService {

    @Autowired
    private TravelPartnerRepository travelPartnerRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


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
    public List<TravelPartner> searchTravelPartners(String country, String startDate, String endDate, String budget, String accommodation) {
        List<Criteria> criteriaList = new ArrayList<>();

        if (country != null) {
            criteriaList.add(Criteria.where("travelDestination.country").is(country));
        }

        if (startDate != null) {
            criteriaList.add(Criteria.where("travelDates.startDate").gte(startDate));
        }

        if (endDate != null) {
            criteriaList.add(Criteria.where("travelDates.endDate").lte(endDate));
        }

        if (budget != null) {
            criteriaList.add(Criteria.where("budget").is(budget));
        }

        if (accommodation != null) {
            criteriaList.add(Criteria.where("travelPreferences.accommodation").is(accommodation));
        }

        Criteria criteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
        Query query = new Query(criteria);

        return mongoTemplate.find(query, TravelPartner.class);
    }

}




