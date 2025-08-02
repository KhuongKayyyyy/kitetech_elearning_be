package com.example.kitetech_elearning_be.message_room_member;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class MessageRoomMemberDTO {

    private UUID messageRoomId;

    private String userId;

    private Boolean isAdmin;

    private LocalDateTime lastSeen;
    private LocalDateTime lastLogin ;

}
