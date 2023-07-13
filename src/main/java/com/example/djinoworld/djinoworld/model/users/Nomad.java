package com.example.djinoworld.djinoworld.model.users;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
* Skills and Expertise {List}
* Areas of Interest {List}
* Travel Preferences {List}
* Social media
* Languages {List}
* Hobbies {List}
* Travel Experience {List} ??
* Work
* Lifestyle ??
*  */

@Document(collection = "Nomad")
@Data
@Getter
@Setter
public class Nomad {

    @Id
    private String nomadID;

    private String work;

    private String language;

}
