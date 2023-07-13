package com.example.djinoworld.djinoworld.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {
    private String username;
    private String password;
    private String email;
    private String userType;
    private String fullName;
    private String location;
}