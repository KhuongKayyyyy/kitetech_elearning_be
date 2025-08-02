package com.example.kitetech_elearning_be.message_room;

import com.example.kitetech_elearning_be.message_content.MessageContentDTO;
import com.example.kitetech_elearning_be.message_room_member.MessageRoomMemberDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class MessageRoomDTO {

    private UUID id;

    private String name;

    private Boolean isGroup;

    private LocalDateTime createdDate;

    private String createdByID;

    private MessageContentDTO lastMessage;
    private List<MessageRoomMemberDTO> members;
    private Long unseenMessageCount;
}
