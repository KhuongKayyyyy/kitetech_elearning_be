package com.example.kitetech_elearning_be.message_room_member;

import com.example.kitetech_elearning_be.chat_user.User;
import com.example.kitetech_elearning_be.message_room.MessageRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "message_room_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(MessageRoomMemberKey.class)
@Builder
public class MessageRoomMember {
    @Id
    @ManyToOne
    @JoinColumn(name = "message_room_id")
    private MessageRoom messageRoom;


    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean isAdmin;

    private LocalDateTime lastSeen;
}
