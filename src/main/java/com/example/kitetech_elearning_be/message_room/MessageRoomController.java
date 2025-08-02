package com.example.kitetech_elearning_be.message_room;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.prefix}/messageroom")
public class MessageRoomController {
    private final MessageRoomService messageRoomService;

    @GetMapping("/find-chat-room")
    public ResponseEntity<MessageRoomDTO> findMessageRoomByMembers(@RequestParam final List<String> members) {
        return ResponseEntity.ok(messageRoomService.findMessageRoomByMembers(members));
    }

    @PostMapping("/create-chat-room")
    public ResponseEntity<MessageRoomDTO> createChatRoom(@RequestParam final List<String> members, @RequestParam final String userName, @RequestParam final String groupName) {
        return ResponseEntity.ok(messageRoomService.createChatRoom(members,userName,groupName));
    }

    @GetMapping("/find-chat-room-at-least-one-content/{username}/{roomId}")
    public ResponseEntity<List<MessageRoomDTO>> findMessageRoomByMembersAtLeastOneContent(@PathVariable final String username, @PathVariable UUID roomId) {
        return ResponseEntity.ok(messageRoomService.findMessageRoomByMembersAtLeastOneContent(roomId,username));
    }
    @GetMapping("/{roomId}")
    public ResponseEntity<MessageRoomDTO> findMessageRoomById( @PathVariable UUID roomId) {
        return ResponseEntity.ok(messageRoomService.findById(roomId));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<MessageRoomDTO>> findMessageRoomsByUsername(@PathVariable final String username) {
        return ResponseEntity.ok(messageRoomService.findMessageRoomByUsername(username));
    }
}
