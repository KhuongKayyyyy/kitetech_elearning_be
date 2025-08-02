package com.example.kitetech_elearning_be.chat_user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByStatus(UserStatus status);
    List<User> findAllByUsernameLikeIgnoreCase(String username);
    List<User> findAllByUsernameIn(List<String> members);
}
