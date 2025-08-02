package com.example.kitetech_elearning_be.message_room_member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRoomMemberRepository extends JpaRepository<MessageRoomMember, MessageRoomMemberKey> {
    List<MessageRoomMember> findByMessageRoomId(final UUID messageRoomId);
    MessageRoomMember findByMessageRoomIdAndUserUsername(final UUID roomId, final String username);
}
