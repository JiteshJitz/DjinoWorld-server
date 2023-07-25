package com.example.djinoworld.djinoworld.repository.users;

import com.example.djinoworld.djinoworld.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event,String> {
    List<Event> findByEventCoordinatorID(String eventCoordinatorID);

}
