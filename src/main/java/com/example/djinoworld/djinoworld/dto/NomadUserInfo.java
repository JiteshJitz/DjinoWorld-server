package com.example.djinoworld.djinoworld.dto;

import com.example.djinoworld.djinoworld.model.User;
import com.example.djinoworld.djinoworld.model.users.Nomad;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class NomadUserInfo {

    private Nomad nomad;

    private UserResponseDTO userResponse;

    public NomadUserInfo(Nomad nomad, UserResponseDTO userResponse) {
        this.nomad = nomad;
        this.userResponse = userResponse;
    }

    private UserResponseDTO fromUser(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getFullName(), user.getLocation());
    }







}
