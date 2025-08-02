package com.example.kitetech_elearning_be.chat_user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserDTO toDTO(final User user, final UserDTO userDTO) {
     userDTO.setUsername(user.getUsername());
     userDTO.setPassword(user.getPassword());
     userDTO.setFullName(user.getFullName());
     userDTO.setStatus(user.getStatus());
     userDTO.setAvatarUrl(user.getAvatarUrl());
    return userDTO;
    }
    public User toEntity(final UserDTO userDTO, final User user) {
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getFullName());
        user.setStatus(userDTO.getStatus());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        return user;
    }

}
