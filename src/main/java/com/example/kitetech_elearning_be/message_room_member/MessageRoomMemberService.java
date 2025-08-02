package com.example.kitetech_elearning_be.message_room_member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageRoomMemberService {
    private final MessageRoomMemberRepository messageRoomMemberRepository;
    private final MessageRoomMemberMapper messageRoomMemberMapper;

    public List<MessageRoomMemberDTO> findByMessageRoomId(final UUID messageRoomId) {
        return messageRoomMemberRepository.findByMessageRoomId(messageRoomId)
                .stream()
                .map(m -> messageRoomMemberMapper.toDTO(m,new MessageRoomMemberDTO())).toList();
    }


    public MessageRoomMemberDTO updateLastSeen(final UUID messageRoomId, final String memberId) {
        final MessageRoomMember  member = messageRoomMemberRepository.findByMessageRoomIdAndUserUsername(messageRoomId,memberId);
        member.setLastSeen(LocalDateTime.now());
        messageRoomMemberRepository.save(member);
        return messageRoomMemberMapper.toDTO(member,new MessageRoomMemberDTO());
    }
}
