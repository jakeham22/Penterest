package com.example.LoginTest.service;

import com.example.LoginTest.domain.User;
import com.example.LoginTest.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * USER CRUD Service
 * 실제 비즈니스 로직 수행
 */
@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 모든 유저 검색
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 유저 아이디 검색
    public Optional<User> getUserByUserId(Long userId) {
        return userRepository.findById(userId);
    }


    // 유저 등록
    public User registerUser(User newUser) {
        if (!userRepository.findById(newUser.getId()).isPresent()) {
            return userRepository.save(newUser);
        }
        String message = String.format("이미 존재하는 user id 입니다. %s" , newUser.getId());
        throw new IllegalArgumentException(message);


        // 400 error
    }

    // 유저 수정 TODO : 삭제될 수도 있는 메서드 [유저수정]
    public User updateUser(User userUpdateInfo) {
        User updateUser = userRepository.save(userUpdateInfo);
        return userRepository.save(updateUser);
    }

    // 유저 삭제
    public List<User> removeUser(Long userId) {
        userRepository.deleteById(userId);
        return getAllUsers();
    }


}
