package com.example.kitetech_elearning_be.message_room;

import com.example.kitetech_elearning_be.chat_user.User;
import com.example.kitetech_elearning_be.chat_user.UserRepository;
import com.example.kitetech_elearning_be.message_content.MessageContentDTO;
import com.example.kitetech_elearning_be.message_content.MessageContentService;
import com.example.kitetech_elearning_be.message_room_member.MessageRoomMember;
import com.example.kitetech_elearning_be.message_room_member.MessageRoomMemberDTO;
import com.example.kitetech_elearning_be.message_room_member.MessageRoomMemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageRoomService {
    private final MessageRoomRepository messageRoomRepository;
    private final MessageRoomMapper messageRoomMapper;
    private final UserRepository userRepository;
    private final MessageContentService messageContentService;
    private final MessageRoomMemberService messageRoomMemberService;


    public List<MessageRoomDTO> findMessageRoomByUsername(final String username) {
        return messageRoomRepository.findMessageRoomsByUsername(username).stream()
                .map(room -> {
                    final MessageRoomDTO messageRoomDTO = messageRoomMapper.toDTO(room, new MessageRoomDTO());
                    final List<MessageRoomMemberDTO> roomMemberDTOs = messageRoomMemberService.findByMessageRoomId(messageRoomDTO.getId());
                    messageRoomDTO.setMembers(roomMemberDTOs);
                    return messageRoomDTO;
                })
                .toList(); // Use .collect(Collectors.toList()) if you're on Java 8
    }


    public MessageRoomDTO findMessageRoomByMembers(final List<String> members){
        return messageRoomRepository.findMessageRoomByMembers(members, members.size())
                .map(m -> {
                    final MessageRoomDTO messageRoomDTO = messageRoomMapper.toDTO(m, new MessageRoomDTO());
                    final List<MessageRoomMemberDTO> roomMemberDTOS = messageRoomMemberService.findByMessageRoomId(messageRoomDTO.getId());
                    messageRoomDTO.setMembers(roomMemberDTOS);
                    return messageRoomDTO;
                }
                ).orElse(null);
    }

    @Transactional
    public MessageRoomDTO createChatRoom(final List<String> members, String username, String groupName) {
        final User user = userRepository.findById(username).orElseThrow();

        // Ensure creator is in the member list
        List<String> allMembers = new ArrayList<>(members);
        if (!allMembers.contains(username)) {
            allMembers.add(username);
        }

        MessageRoom messageRoom = MessageRoom.builder()
                .isGroup(allMembers.size() > 2)
                .name(groupName)
                .createdBy(user)
                .createdDate(LocalDateTime.now())
                .members(new ArrayList<>())
                .build();

        final List<User> users = userRepository.findAllByUsernameIn(allMembers);

        users.forEach(u -> {
            final MessageRoomMember messageRoomMember = MessageRoomMember.builder()
                    .messageRoom(messageRoom)
                    .user(u)
                    .isAdmin(u.getUsername().equals(username))
                    .lastSeen(LocalDateTime.now())
                    .build();
            messageRoom.getMembers().add(messageRoomMember);
        });

        MessageRoom saved = messageRoomRepository.save(messageRoom);

        final MessageRoomDTO roomDTO = messageRoomMapper.toDTO(saved, new MessageRoomDTO());
        final List<MessageRoomMemberDTO> roomMembers = messageRoomMemberService.findByMessageRoomId(roomDTO.getId());
        roomDTO.setMembers(roomMembers);
        return roomDTO;
    }

    public List<MessageRoomDTO> findMessageRoomByMembersAtLeastOneContent(final UUID roomId, final String userName){
        return messageRoomRepository.findMessageRoomByMembersAtLeastOneContent(userName)
                .stream()
                .map(m ->{
                       final MessageRoomDTO  roomDTO =   messageRoomMapper.toDTO(m,new MessageRoomDTO());
                       roomDTO.setUnseenMessageCount(messageContentService.countUnseenMessage(roomId,userName));
                       final MessageContentDTO lastMessage = messageContentService.getLastMessage(m.getId());
                       roomDTO.setLastMessage(lastMessage);
                       final List<MessageRoomMemberDTO> members = messageRoomMemberService.findByMessageRoomId(roomDTO.getId());
                       roomDTO.setMembers(members);
                       return roomDTO;
                })
                .toList();
    }

    public MessageRoomDTO findById(final UUID roomId) {
        return messageRoomRepository.findById(roomId)
                .map(room -> {
                    final MessageRoomDTO roomDTO = messageRoomMapper.toDTO(room, new MessageRoomDTO());
                    final List<MessageRoomMemberDTO> roomMembers = messageRoomMemberService.findByMessageRoomId(roomDTO.getId());
                    roomDTO.setMembers(roomMembers);
                    return roomDTO;
                })
                .orElse(null);
    }
}
