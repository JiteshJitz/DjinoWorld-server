package com.example.djinoworld.djinoworld.service.users;

import com.example.djinoworld.djinoworld.model.users.FriendRequest;
import com.example.djinoworld.djinoworld.model.users.Nomad;
import com.example.djinoworld.djinoworld.repository.users.NomadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestService {

    @Autowired
    private NomadRepository nomadRepository;

    public String sendFriendRequest(FriendRequest friendRequest) {
        Nomad sender = nomadRepository.findById(friendRequest.getNomadId()).orElse(null);
        Nomad receiver = nomadRepository.findById(friendRequest.getFriendId()).orElse(null);

        if(sender == null || receiver == null) {
            return "Either the sender or the receiver does not exist.";
        }

        if(sender.getListOfFriends().contains(friendRequest.getFriendId())) {
            return "You're already friends with this user.";
        }

        if(!receiver.getListOfFriendRequests().contains(friendRequest.getNomadId())) {
            receiver.getListOfFriendRequests().add(friendRequest.getNomadId());
            nomadRepository.save(receiver);
        }

        return "Friend request sent.";
    }

    // Further methods like accepting or rejecting a friend request can be implemented here
}
