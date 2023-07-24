package com.example.djinoworld.djinoworld.service.users;


import com.example.djinoworld.djinoworld.dto.NomadUserInfo;
import com.example.djinoworld.djinoworld.model.User;
import com.example.djinoworld.djinoworld.model.users.Nomad;
import com.example.djinoworld.djinoworld.repository.users.NomadRepository;
import com.example.djinoworld.djinoworld.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NomadService {

    @Autowired
    private NomadRepository nomadRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManager userService;

    // Crud create, read, update, delete

    public Nomad addNomad(Nomad nomad) {
        return nomadRepository.save(nomad);
    }

    public List<Nomad> findAllNomads() {
        return nomadRepository.findAll();
    }

    public Nomad getNomadByNomadID(String nomadID){
        return nomadRepository.findById(nomadID).get();
    }

//   Individual put

    public String deleteNomad(String nomadID){
        nomadRepository.deleteById(nomadID);
        return "This user has been deleted " + nomadID;
    }

    public NomadUserInfo getNomadAndUserByNomadID(String nomadID){
        Nomad nomad = nomadRepository.findById(nomadID).get();
        Optional<User> userOpt = userService.findById(nomad.getUserID());  // Update this line
        if (userOpt.isPresent()) {
            return new NomadUserInfo(nomad, userOpt.get());
        } else {
            throw new RuntimeException("User not found");
        }
    }


}
