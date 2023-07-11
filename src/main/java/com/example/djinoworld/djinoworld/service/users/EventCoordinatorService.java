package com.example.djinoworld.djinoworld.service.users;


import com.example.djinoworld.djinoworld.model.users.EventCoordinator;
import com.example.djinoworld.djinoworld.model.users.Nomad;
import com.example.djinoworld.djinoworld.repository.users.EventCoordinatorRepository;
import com.example.djinoworld.djinoworld.repository.users.NomadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventCoordinatorService {

    @Autowired
    private EventCoordinatorRepository eventCoordinatorRepository;

    // Crud create, read, update, delete

    public EventCoordinator addCoordinator(EventCoordinator coordinator) {
        coordinator.setEventCoordinatorID(UUID.randomUUID().toString());
        return eventCoordinatorRepository.save(coordinator);
    }

    public List<EventCoordinator> findAllCoordinators() {
        return eventCoordinatorRepository.findAll();
    }

    public EventCoordinator getCoordinatorByCoordinatorID(String coordinatorID){
        return eventCoordinatorRepository.findById(coordinatorID).get();
    }

    //   Individual put

    public String deleteCoordinator(String coordinatorID){
        eventCoordinatorRepository.deleteById(coordinatorID);
        return "This user has been deleted " + coordinatorID;
    }


}
