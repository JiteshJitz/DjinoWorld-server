package com.example.djinoworld.djinoworld.repository.users;

import com.example.djinoworld.djinoworld.model.users.EventCoordinator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCoordinatorRepository extends MongoRepository<EventCoordinator,String> {
}
