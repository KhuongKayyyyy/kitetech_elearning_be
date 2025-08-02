package com.example.kitetech_elearning_be.message_content;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MessageContentDTO {


    private UUID id;

    private String content;

    private LocalDateTime dateSent;

    private MessageType messageType;

    private UUID messageRoomId;

    private String userId;
}
