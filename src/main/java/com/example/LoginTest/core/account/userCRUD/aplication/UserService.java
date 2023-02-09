package com.example.LoginTest.core.account.userCRUD.aplication;


import com.example.LoginTest.core.account.domain.User;

import com.example.LoginTest.core.account.userCRUD.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * USER CRUD Service
 * 실제 비즈니스 로직 수행
 *
 *
 * Dto 사용해보자!
 * DTO : Entity 클래스에서 필요한 데이터만 선택적으로 DTO에 담아서 생성해 사용함으로써, Entitiy 클래스를 감추며 보호
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
