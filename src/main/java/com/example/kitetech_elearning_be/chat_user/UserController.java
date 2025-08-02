package com.example.kitetech_elearning_be.chat_user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.prefix}/chat_user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody final UserDTO userDTO) {
        return ResponseEntity.ok(userService.login(userDTO));
    }

    @MessageMapping("/user/connect") //receives message from clients sending to /app/user/connect
    @SendTo("/topic/active") // send the response to all clients subscribe to /topic/active
    public UserDTO connect(@RequestBody UserDTO userDTO) {
        return userService.connect(userDTO);
    }

    @MessageMapping("/user/disconnect") //receives message from clients sending to /app/user/connect
    @SendTo("/topic/active") // send the response to all clients subscribe to /topic/active
    public UserDTO disconnect(@RequestBody UserDTO userDTO) {
        return userService.logout(userDTO.getUsername());
    }

    @GetMapping("/online")
    public ResponseEntity<List<UserDTO>> getOnlineUser() {
       return ResponseEntity.ok(userService.getOnlineUsers());
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<List<UserDTO>> searchUserByUserName(@PathVariable("username") final String userName) {
        return ResponseEntity.ok(userService.searchUserByUserName(userName));
    }


}
