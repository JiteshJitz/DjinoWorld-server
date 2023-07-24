package com.example.djinoworld.djinoworld.service.users;

import com.example.djinoworld.djinoworld.model.users.Friendship;
import com.example.djinoworld.djinoworld.model.users.Nomad;
import com.example.djinoworld.djinoworld.repository.users.FriendshipRepository;
import com.example.djinoworld.djinoworld.repository.users.NomadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService {

    @Autowired
    private NomadRepository nomadRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    public String sendFriendRequest(Friendship friendship) {
        Nomad sender = nomadRepository.findById(friendship.getSenderId()).orElse(null);
        Nomad receiver = nomadRepository.findById(friendship.getReceiverId()).orElse(null);

        if (sender == null || receiver == null) {
            // Handle case where sender or receiver does not exist
            // Return an error message or throw an exception
            return "Sender or receiver does not exist.";
        }

        // Check if friendship already exists
// Check if friendship already exists
        List<Friendship> existingFriendships = friendshipRepository.findBySenderIdAndReceiverId(
                sender.getNomadID(),
                receiver.getNomadID()
        );

        if (existingFriendships.isEmpty()) {
            // If not, create and save new friendship
            Friendship newFriendship = new Friendship();
            newFriendship.setSenderId(sender.getNomadID());
            newFriendship.setReceiverId(receiver.getNomadID());
            Friendship savedFriendship = friendshipRepository.save(newFriendship);

            if (!receiver.getListOfFriendships().contains(savedFriendship.getId())) {
                receiver.getListOfFriendships().add(savedFriendship.getId());
                nomadRepository.save(receiver);
            }

            return "Friend request sent successfully.";
        } else {
            // Handle the case where friendship already exists
            // Return a message indicating that the friend request has already been sent
            return "Friend request already exists.";
        }
    }



    // Further methods like accepting or rejecting a friend request can be implemented here
}
