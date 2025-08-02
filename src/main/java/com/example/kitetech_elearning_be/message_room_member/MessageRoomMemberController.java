package com.example.kitetech_elearning_be.message_room_member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/messageroommember")
public class MessageRoomMemberController {
    private final MessageRoomMemberService messageRoomMemberService;

    @PostMapping("/update-last-seen/{roomId}/{memberId}")
    public ResponseEntity<MessageRoomMemberDTO> updateLastSeen(@PathVariable UUID roomId, @PathVariable String memberId) {
        return ResponseEntity.ok(messageRoomMemberService.updateLastSeen(roomId,memberId));
    }
}
