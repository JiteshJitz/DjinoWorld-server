package com.example.djinoworld.djinoworld.repository.users;

import com.example.djinoworld.djinoworld.model.users.Nomad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NomadRepository extends MongoRepository<Nomad,String> {
}
