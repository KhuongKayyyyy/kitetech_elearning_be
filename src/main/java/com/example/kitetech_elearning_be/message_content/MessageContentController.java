package com.example.kitetech_elearning_be.message_content;

import com.example.kitetech_elearning_be.message_room_member.MessageRoomMemberDTO;
import com.example.kitetech_elearning_be.message_room_member.MessageRoomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/messagecontents")
public class MessageContentController {
    private final MessageContentService messageContentService;
    private final MessageRoomMemberService messageRoomMemberService;
    private final SimpMessagingTemplate messagingTemplate;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/{roomId}")
    public ResponseEntity<List<MessageContentDTO>> getMessageByRoomId(@PathVariable("roomId") final UUID roomId) {
        return ResponseEntity.ok(messageContentService.getMessageByRoomId(roomId));
    }


    @MessageMapping("/send-message") //receives message from clients sending to /app/user/connect
    public void sendMessage(@RequestBody MessageContentDTO messageContentDTO) {
        final MessageContentDTO saved = messageContentService.save(messageContentDTO);
        List<MessageRoomMemberDTO> members = messageRoomMemberService.findByMessageRoomId(saved.getId());
        members.forEach(member -> {
            simpMessagingTemplate.convertAndSendToUser(
                    member.getUserId(),
                    "/queue/messages",
                    saved
            );
        });
    }
}
