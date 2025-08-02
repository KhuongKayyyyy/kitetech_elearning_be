package com.example.kitetech_elearning_be.message_room_member;

import com.example.kitetech_elearning_be.chat_user.User;
import com.example.kitetech_elearning_be.message_room.MessageRoom;
import lombok.Data;

@Data
public class MessageRoomMemberKey {
    private User user;
    private MessageRoom messageRoom;

}
