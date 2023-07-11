package com.example.djinoworld.djinoworld.service.users;

import com.example.djinoworld.djinoworld.model.users.Accommodation;
import com.example.djinoworld.djinoworld.repository.users.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AccommodationService {


    @Autowired
    private AccommodationRepository accommodationRepository;

    // Crud create, read, update, delete

    public Accommodation addAccommodation(Accommodation accommodation) {
        accommodation.setAccommodationID(UUID.randomUUID().toString());
        return accommodationRepository.save(accommodation);
    }

    public List<Accommodation> findAllAccommodation() {
        return accommodationRepository.findAll();
    }

    public Accommodation getAccommodationByAccommodationID(String accommodationID){
        return accommodationRepository.findById(accommodationID).get();
    }

    //   Individual put

    public String deleteAccommodation(String accommodationID){
        accommodationRepository.deleteById(accommodationID);
        return "This user has been deleted " + accommodationID;
    }

}
