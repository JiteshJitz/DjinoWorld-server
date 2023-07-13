package com.example.djinoworld.djinoworld.repository.users;

import com.example.djinoworld.djinoworld.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event,String> {
}
