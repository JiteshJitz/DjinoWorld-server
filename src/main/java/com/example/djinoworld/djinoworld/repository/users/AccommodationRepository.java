package com.example.djinoworld.djinoworld.repository.users;

import com.example.djinoworld.djinoworld.model.Accommodation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends MongoRepository<Accommodation,String> {
}
