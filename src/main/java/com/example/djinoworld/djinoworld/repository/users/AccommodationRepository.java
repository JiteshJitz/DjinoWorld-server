package com.example.djinoworld.djinoworld.repository.users;

import com.example.djinoworld.djinoworld.model.Accommodation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends MongoRepository<Accommodation,String> {
    List<Accommodation> findByOwnerID(String ownerId);

}
