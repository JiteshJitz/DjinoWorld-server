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
    private User user;

    public NomadUserInfo(Nomad nomad, User user) {
        this.nomad = nomad;
        this.user = user;
    }

    public Nomad getNomad() {
        return nomad;
    }

    public User getUser() {
        return user;
    }
}
