package com.example.djinoworld.djinoworld.service.users;

import com.example.djinoworld.djinoworld.model.users.Friendship;
import com.example.djinoworld.djinoworld.model.users.Nomad;
import com.example.djinoworld.djinoworld.repository.users.FriendshipRepository;
import com.example.djinoworld.djinoworld.repository.users.NomadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        // Check if receiver is already in sender's friend list
        if (sender.getListOfFriends().contains(receiver.getNomadID())) {
            return "This user is already your friend.";
        }


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

    public String acceptFriendRequest(String friendshipId, String receiverId) {
        // find friendship
        Friendship friendship = friendshipRepository.findById(friendshipId).orElse(null);

        if (friendship == null) {
            // Handle case where friendship does not exist
            return "Friendship does not exist.";
        }


        if (!friendship.getReceiverId().equals(receiverId)) {
            // Handle case where receiver is not the one accepting the friend request
            return "Only the receiver can accept the friend request.";
        }

        if (friendship.isAccepted()) {
            // Handle case where friend request has already been accepted
            return "Friend request already accepted.";
        }

        // Find sender and receiver
        Nomad sender = nomadRepository.findById(friendship.getSenderId()).orElse(null);
        Nomad receiver = nomadRepository.findById(receiverId).orElse(null);

        if (sender == null || receiver == null) {
            // Handle case where sender or receiver does not exist
            return "Sender or receiver does not exist.";
        }



        // Add each other to friends list
        if (!sender.getListOfFriends().contains(receiverId)) {
            sender.getListOfFriends().add(receiverId);
            nomadRepository.save(sender);
        }

        if (!receiver.getListOfFriends().contains(sender.getNomadID())) {
            receiver.getListOfFriends().add(sender.getNomadID());
            nomadRepository.save(receiver);
        }

        // Remove friendship from listOfFriendships and set friendship to accepted
        receiver.getListOfFriendships().remove(friendshipId);
        nomadRepository.save(receiver);

        // Delete the friendship from the Friendship collection
        friendshipRepository.delete(friendship);

        return "Friend request accepted successfully.";
    }

    public String rejectFriendRequest(String friendshipId) {
        // find friendship by id
        Optional<Friendship> friendshipOptional = friendshipRepository.findById(friendshipId);

        if (friendshipOptional.isPresent()) {
            Friendship friendship = friendshipOptional.get();
            // find receiver and remove the friendshipId from listOfFriendships
            Nomad receiver = nomadRepository.findById(friendship.getReceiverId()).orElse(null);
            if (receiver != null) {
                receiver.getListOfFriendships().remove(friendshipId);
                nomadRepository.save(receiver);
            }

            // delete the friendship
            friendshipRepository.deleteById(friendshipId);

            return "Friend request rejected successfully.";
        } else {
            return "No friend request found with the provided id.";
        }
    }



    /*Cases:
        1. Send the friend request
        2. Accept the friend request
        3. If user has already sent the friend request
        4. If the user already a friend

     */



    // Further methods like accepting or rejecting a friend request can be implemented here
}
