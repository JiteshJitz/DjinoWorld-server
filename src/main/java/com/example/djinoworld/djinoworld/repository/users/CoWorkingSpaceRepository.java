package com.example.djinoworld.djinoworld.repository.users;

import com.example.djinoworld.djinoworld.model.CoWorkingSpace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoWorkingSpaceRepository extends MongoRepository<CoWorkingSpace,String> {
    List<CoWorkingSpace> findByOwnerId(String ownerId);

}
