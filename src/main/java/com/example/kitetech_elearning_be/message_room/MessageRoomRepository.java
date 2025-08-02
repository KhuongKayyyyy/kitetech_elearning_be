package com.example.kitetech_elearning_be.message_room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface MessageRoomRepository extends JpaRepository<MessageRoom, UUID> {
    @Query("""
    SELECT messageRoom
    FROM MessageRoom messageRoom
    JOIN MessageRoomMember messageRoomMember
        ON messageRoomMember.messageRoom = messageRoom
    GROUP BY messageRoom.id
    HAVING count(CASE WHEN messageRoomMember.user.username in :members then 1 end) = :size
        AND count(*) =: size
    """)
    Optional<MessageRoom> findMessageRoomByMembers(List<String> members, final int size);

    @Query("""
    SELECT messageRoom
    FROM MessageRoom messageRoom
    JOIN MessageRoomMember messageRoomMember
        ON messageRoomMember.messageRoom = messageRoom
    JOIN MessageContent messageContent
        ON messageContent.messageRoom = messageRoom
    WHERE messageRoomMember.user.username = :userName
    GROUP BY messageRoom.id
    HAVING COUNT(messageContent) > 0
    ORDER BY count(messageContent.dateSent) DESC
    """)
    List<MessageRoom> findMessageRoomByMembersAtLeastOneContent(final String userName);


    @Query("""
    SELECT messageRoom
    FROM MessageRoom messageRoom
    JOIN MessageRoomMember messageRoomMember
      ON messageRoomMember.messageRoom = messageRoom
    WHERE messageRoomMember.user.username = :username
    """)
    List<MessageRoom> findMessageRoomsByUsername(String username);


    @Query("""
SELECT messageRoom
FROM MessageRoom messageRoom
JOIN MessageRoomMember messageRoomMember
  ON messageRoomMember.messageRoom = messageRoom
LEFT JOIN MessageContent messageContent
  ON messageContent.messageRoom = messageRoom
WHERE messageRoomMember.user.username = :username
GROUP BY messageRoom.id
ORDER BY MAX(messageContent.dateSent) DESC
""")
    List<MessageRoom> findUserChatRoomsOrderedByLatestMessage(String username);

}
