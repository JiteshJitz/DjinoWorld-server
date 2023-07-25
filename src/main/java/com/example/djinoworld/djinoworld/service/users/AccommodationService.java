package com.example.djinoworld.djinoworld.service.users;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.example.djinoworld.djinoworld.model.Accommodation;
import com.example.djinoworld.djinoworld.repository.users.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AccommodationService {


    @Autowired
    private AccommodationRepository accommodationRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    // Crud create, read, update, delete

    public Accommodation addAccommodation(Accommodation accommodation) {
        accommodation.setAccommodationID(UUID.randomUUID().toString());
        return accommodationRepository.save(accommodation);
    }

    public List<Accommodation> findAllAccommodation() {
        return accommodationRepository.findAll();
    }

    public Accommodation getAccommodationByAccommodationID(String accommodationID){
        return accommodationRepository.findById(accommodationID).get();
    }

    //   Individual put

    public String deleteAccommodation(String accommodationID){
        accommodationRepository.deleteById(accommodationID);
        return "This user has been deleted " + accommodationID;
    }

    public List<Accommodation> getAccommodationByOwnerID(String ownerId) {
        return accommodationRepository.findByOwnerID(ownerId);
    }

    public List<Accommodation> filterAccommodations(String address, Double minPrice, Double maxPrice, String accommodationType){
        Query query = new Query();

        if (address != null) {
            query.addCriteria(Criteria.where("address").is(address));
        }

        if (minPrice != null && maxPrice != null) {
            query.addCriteria(Criteria.where("price").gte(minPrice).lte(maxPrice));
        } else if (minPrice != null) {
            query.addCriteria(Criteria.where("price").gte(minPrice));
        } else if (maxPrice != null) {
            query.addCriteria(Criteria.where("price").lte(maxPrice));
        }

        if (accommodationType != null) {
            query.addCriteria(Criteria.where("accommodationType").is(accommodationType));
        }

        return mongoTemplate.find(query, Accommodation.class);
    }

}
