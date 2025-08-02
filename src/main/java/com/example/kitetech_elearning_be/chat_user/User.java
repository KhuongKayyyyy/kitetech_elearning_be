package com.example.kitetech_elearning_be.chat_user;

import com.example.kitetech_elearning_be.message_room.MessageRoom;
import com.example.kitetech_elearning_be.message_room_member.MessageRoomMember;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chat_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private  String username;
    private  String password;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private LocalDateTime lastLogin = LocalDateTime.now();

    private String avatarUrl;

    @OneToMany(mappedBy = "createdBy")
    private List<MessageRoom> messageRooms;

    @OneToMany(mappedBy = "user")
    private List<MessageRoomMember> messageMembers;
}