package com.example.kitetech_elearning_be.chat_user;

import lombok.Data;

@Data
public class UserDTO {
    private  String username;
    private  String password;
    private String fullName;
    private UserStatus status;
    private String avatarUrl;
}
