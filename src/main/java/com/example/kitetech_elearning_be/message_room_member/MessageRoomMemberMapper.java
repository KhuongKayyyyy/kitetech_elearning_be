package com.example.kitetech_elearning_be.message_room_member;

import com.example.kitetech_elearning_be.chat_user.UserRepository;
import com.example.kitetech_elearning_be.chat_user.User;
import com.example.kitetech_elearning_be.message_room.MessageRoomRepository;
import com.example.kitetech_elearning_be.message_room.MessageRoom;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageRoomMemberMapper {
    private final UserRepository userRepository;
    private final MessageRoomRepository messageRoomRepository;

    public MessageRoomMemberDTO toDTO(final MessageRoomMember messageRoomMember, final MessageRoomMemberDTO messageRoomMemberDTO) {

//        private UUID messageRoomId;
//
//        private Long userId;
//
//        private Boolean isAdmin;
//
//        private LocalDateTime lastSeen;
//        private LocalDateTime lastLogin ;
        messageRoomMemberDTO.setMessageRoomId(messageRoomMember.getMessageRoom().getId());
        messageRoomMemberDTO.setUserId(messageRoomMember.getUser().getUsername()
        );
        messageRoomMemberDTO.setIsAdmin(messageRoomMember.getIsAdmin());
        messageRoomMemberDTO.setLastSeen(messageRoomMember.getLastSeen()    );
        messageRoomMemberDTO.setLastLogin(messageRoomMember.getUser().getLastLogin());
        return messageRoomMemberDTO;
    }

    public MessageRoomMember toEntity(final MessageRoomMemberDTO messageRoomMemberDTO,final MessageRoomMember messageRoomMember) {
//        private MessageRoom messageRoom;
//
//
//        @Id
//        @ManyToOne
//        @JoinColumn(name = "user_id")
//        private User user;
//
//        private Boolean isAdmin;
//
//        private LocalDateTime lastSeen;
        final MessageRoom messageRoom = messageRoomMemberDTO.getMessageRoomId() == null ? null : messageRoomRepository.findById(messageRoomMemberDTO.getMessageRoomId()).orElseThrow(() -> new EntityNotFoundException("MessageRoom not found"));
        messageRoomMember.setMessageRoom(messageRoom);
        final User user = messageRoomMemberDTO.getUserId() == null ? null : userRepository.findById(messageRoomMemberDTO.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        messageRoomMember.setUser(user);
        messageRoomMember.setIsAdmin(messageRoomMemberDTO.getIsAdmin());
        messageRoomMember.setLastSeen(messageRoomMemberDTO.getLastSeen());
        return messageRoomMember;
    }
}
