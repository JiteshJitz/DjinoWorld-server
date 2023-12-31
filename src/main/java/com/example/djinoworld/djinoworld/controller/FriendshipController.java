package com.example.djinoworld.djinoworld.controller;

import com.example.djinoworld.djinoworld.model.users.Friendship;
import com.example.djinoworld.djinoworld.service.users.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friendship")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    @PostMapping("/request")
    public ResponseEntity<String> sendFriendRequest(@RequestBody Friendship friendship) {
        String response = friendshipService.sendFriendRequest(friendship);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/accept/{friendshipId}/{receiverId}")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable String friendshipId, @PathVariable String receiverId) {
        String response = friendshipService.acceptFriendRequest(friendshipId, receiverId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/request/{id}")
    public ResponseEntity<String> rejectFriendRequest(@PathVariable String id) {
        String response = friendshipService.rejectFriendRequest(id);
        return ResponseEntity.ok(response);
    }



    // Additional methods for accepting/rejecting friend requests, viewing a user's friend requests, etc.
}
