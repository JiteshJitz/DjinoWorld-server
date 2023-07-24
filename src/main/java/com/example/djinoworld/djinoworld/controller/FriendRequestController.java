package com.example.djinoworld.djinoworld.controller;


import com.example.djinoworld.djinoworld.model.users.FriendRequest;
import com.example.djinoworld.djinoworld.service.users.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friendRequest")
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    @PostMapping
    public ResponseEntity<String> sendFriendRequest(@RequestBody FriendRequest friendRequest) {
        String message = friendRequestService.sendFriendRequest(friendRequest);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // Further methods like accepting or rejecting a friend request can be implemented here
}
