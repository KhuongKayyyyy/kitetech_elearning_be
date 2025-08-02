package com.example.kitetech_elearning_be.message_room;

import com.example.kitetech_elearning_be.chat_user.User;
import com.example.kitetech_elearning_be.chat_user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageRoomMapper {
    private final UserRepository userRepository;
    public MessageRoomDTO toDTO(final MessageRoom messageRoom, final MessageRoomDTO messageRoomDTO) {
        messageRoomDTO.setId(messageRoom.getId());
        messageRoomDTO.setName(messageRoom.getName());
        messageRoomDTO.setIsGroup(messageRoom.getIsGroup());
        messageRoomDTO.setCreatedDate(messageRoom.getCreatedDate());
        messageRoomDTO.setCreatedByID(
                messageRoom.getCreatedBy() != null ? messageRoom.getCreatedBy().getUsername() : null
        );
        return messageRoomDTO;
    }

    public MessageRoom toEntity(final  MessageRoomDTO messageRoomDTO, final MessageRoom messageRoom) {
        messageRoom.setId(messageRoomDTO.getId());
        messageRoom.setName(messageRoomDTO.getName());
        messageRoom.setIsGroup(messageRoomDTO.getIsGroup());
        messageRoom.setCreatedDate(messageRoomDTO.getCreatedDate());
        final User user = messageRoomDTO.getCreatedByID() == null ? null : userRepository.findById(messageRoomDTO.getCreatedByID()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        messageRoom.setCreatedBy(user);
        return messageRoom;
    }
}
