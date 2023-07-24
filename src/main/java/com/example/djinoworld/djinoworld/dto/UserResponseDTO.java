package com.example.djinoworld.djinoworld.dto;

import com.example.djinoworld.djinoworld.model.User;
import lombok.*;

@Getter
@Setter
@Data
public class UserResponseDTO {
    private String id;
    private String username;
    private String email;
    private String fullName;
    private String location;

    public UserResponseDTO(String id, String username, String email, String fullName, String location) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.location = location;
    }

    public static UserResponseDTO fromUser(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getLocation()
        );
    }


    // constructors, getters and setters
}
