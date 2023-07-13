package com.example.djinoworld.djinoworld.dto;

import com.example.djinoworld.djinoworld.model.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
    private String id;
    private String username;

    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}