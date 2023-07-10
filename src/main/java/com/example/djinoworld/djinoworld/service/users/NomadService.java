package com.example.djinoworld.djinoworld.service.users;


import com.example.djinoworld.djinoworld.model.users.Nomad;
import com.example.djinoworld.djinoworld.repository.users.NomadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NomadService {

    @Autowired
    private NomadRepository nomadRepository;

    // Crud create, read, update, delete

    public Nomad addNomad(Nomad nomad) {
        nomad.setNomadID(UUID.randomUUID().toString());
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

}
