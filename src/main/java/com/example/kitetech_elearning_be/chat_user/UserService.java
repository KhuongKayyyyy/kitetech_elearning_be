package com.example.kitetech_elearning_be.chat_user;

import com.example.kitetech_elearning_be.exception.InvalidCredentialException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO login(final UserDTO userDTO) {
        final User user = userRepository.findById(userDTO.getUsername()).orElseGet(() -> createUser(userDTO));
        validatePassword(userDTO, user.getPassword());
        user.setStatus(UserStatus.ONLINE);
        userRepository.save(user);
        return userMapper.toDTO(user, new UserDTO());
    }



    private void validatePassword(final UserDTO userDTO, final String password) {
        if(!password.equals(userDTO.getPassword())) {
            throw new InvalidCredentialException("Invalid password");
        }

    }

    private User createUser(final UserDTO userDTO) {
        final User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .fullName(userDTO.getFullName())
                .status(UserStatus.ONLINE)
                .lastLogin(LocalDateTime.now())
                .build();


        return userRepository.save(user);
    }

    public  UserDTO connect(UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userDTO.getUsername());
        user.ifPresent(u ->{
            u.setStatus(UserStatus.ONLINE);
            userRepository.save(u);
        });
        return user.map(u -> userMapper.toDTO(u, new UserDTO())).orElse(null);
    }

    public List<UserDTO> getOnlineUsers() {
        return userRepository.findAllByStatus(UserStatus.ONLINE).stream().map(u -> userMapper.toDTO(u, new UserDTO())).toList();
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(u -> userMapper.toDTO(u, new UserDTO())).collect(Collectors.toList());
    }


    public UserDTO logout(final String username) {
        Optional<User> user = userRepository.findById(username);
        user.ifPresent(u ->{
            u.setStatus(UserStatus.OFFLINE);
            userRepository.save(u);

        });
        return user.map(u -> userMapper.toDTO(u, new UserDTO())).orElse(null);
    }




    public List<UserDTO> searchUserByUserName(final String userName) {
        return userRepository.findAllByUsernameLikeIgnoreCase(userName).stream().map(u -> userMapper.toDTO(u, new UserDTO())).collect(Collectors.toList());
    }

}
