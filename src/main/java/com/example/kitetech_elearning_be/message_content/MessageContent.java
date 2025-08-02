package com.example.kitetech_elearning_be.message_content;

import com.example.kitetech_elearning_be.chat_user.User;
import com.example.kitetech_elearning_be.message_room.MessageRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "message_content")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageContent {

    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.AUTO)
    private UUID id;

    private String content;

    @CreatedDate
    private LocalDateTime dateSent;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @ManyToOne
    @JoinColumn(name = "message_room_id")
    private MessageRoom messageRoom;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
