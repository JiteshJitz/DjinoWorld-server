package com.example.djinoworld.djinoworld.repository.users;

import com.example.djinoworld.djinoworld.model.users.Friendship;
import com.example.djinoworld.djinoworld.model.users.Nomad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FriendshipRepository extends MongoRepository<Friendship, String> {
    List<Friendship> findBySenderIdAndReceiverId(String senderId, String receiverId);
}

