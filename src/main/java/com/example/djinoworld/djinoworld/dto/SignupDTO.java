package com.example.djinoworld.djinoworld.dto;

import com.example.djinoworld.djinoworld.model.Role;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {
    private String username;
    private String password;
    private String email;
    private Role role;
    private String fullName;
    private String location;
}