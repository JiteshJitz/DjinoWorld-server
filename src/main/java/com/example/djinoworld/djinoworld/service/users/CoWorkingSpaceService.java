package com.example.djinoworld.djinoworld.service.users;


import com.example.djinoworld.djinoworld.dto.CoWorkingSpaceResponseDTO;
import com.example.djinoworld.djinoworld.model.CoWorkingSpace;
import com.example.djinoworld.djinoworld.repository.users.CoWorkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CoWorkingSpaceService {

    @Autowired
    private CoWorkingSpaceRepository coWorkingSpaceRepository;

    // Crud create, read, update, delete

    public CoWorkingSpace addCoWorkingSpace(CoWorkingSpace coWorkingSpace) {
        coWorkingSpace.setCoWorkingSpaceID(UUID.randomUUID().toString());
        return coWorkingSpaceRepository.save(coWorkingSpace);
    }

    public List<CoWorkingSpace> findAllCoWorkingSpaces() {
        return coWorkingSpaceRepository.findAll();
    }

    public CoWorkingSpace getCoWorkingSpaceByID(String coWorkingSpaceID){
        return coWorkingSpaceRepository.findById(coWorkingSpaceID).get();
    }

    //   Individual put

    public String deleteCoWorkingSpace(String coWorkingSpaceID){
        coWorkingSpaceRepository.deleteById(coWorkingSpaceID);
        return "This user has been deleted " + coWorkingSpaceID;
    }

    public List<CoWorkingSpaceResponseDTO> getCoWorkingSpacesByOwnerID(String ownerId) {
        List<CoWorkingSpace> coWorkingSpaces = coWorkingSpaceRepository.findByOwnerId(ownerId);
        return coWorkingSpaces.stream()
                .map(CoWorkingSpaceResponseDTO::new)
                .collect(Collectors.toList());
    }







}
