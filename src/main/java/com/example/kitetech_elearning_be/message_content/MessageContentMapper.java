package com.example.kitetech_elearning_be.message_content;

import com.example.kitetech_elearning_be.chat_user.User;
import com.example.kitetech_elearning_be.chat_user.UserRepository;
import com.example.kitetech_elearning_be.message_room.MessageRoom;
import com.example.kitetech_elearning_be.message_room.MessageRoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageContentMapper {

    private final MessageRoomRepository messageRoomRepository;
    private final UserRepository userRepository;

    public MessageContentDTO toDTO(final MessageContent messageContent, final MessageContentDTO messageContentDTO) {

        messageContentDTO.setId(messageContent.getId());
        messageContentDTO.setContent(messageContent.getContent());
        messageContentDTO.setDateSent(messageContent.getDateSent());
        messageContentDTO.setMessageType(messageContent.getMessageType());
        messageContentDTO.setMessageRoomId(messageContent.getMessageRoom().getId());
        messageContentDTO.setUserId(messageContent.getUser().getUsername());
        return messageContentDTO;
    }

    public MessageContent toEntity(final MessageContentDTO messageContentDTO, final MessageContent messageContent) {
        messageContent.setId(messageContentDTO.getId());
        messageContent.setContent(messageContentDTO.getContent());
        messageContent.setDateSent(messageContentDTO.getDateSent());
        messageContent.setMessageType(messageContentDTO.getMessageType());
        final MessageRoom messageRoom = messageContentDTO.getMessageRoomId() == null ? null : messageRoomRepository.findById(messageContentDTO.getMessageRoomId()).orElseThrow(() -> new EntityNotFoundException("MessageRoom not found"));
        messageContent.setMessageRoom(messageRoom);
        final User user = messageContentDTO.getUserId() == null ? null : userRepository.findById(messageContentDTO.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        messageContent.setUser(user);

        return messageContent;

    }
}
