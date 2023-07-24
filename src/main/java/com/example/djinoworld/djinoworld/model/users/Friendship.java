package com.example.djinoworld.djinoworld.model.users;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@Document(collection = "Friendship")
public class Friendship {

    @Id
    private String id;

    private String senderId;

    private String receiverId;

    private String message;

    private boolean accepted;

}
