package com.example.djinoworld.djinoworld.repository.users;

import com.example.djinoworld.djinoworld.model.users.CoWorkingSpace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoWorkingSpaceRepository extends MongoRepository<CoWorkingSpace,String> {
}
