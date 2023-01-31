package com.example.LoginTest.dto;

import com.example.LoginTest.domain.User;

/**
 * User CRUD Dto
 * 회원가입을 위해서 User 중 필요한 정보를 넘길 Dto Class
 *
 */
public class UserDto {
    private User user;

    /**
     * Id, password는 데이터변경 금지를 위해 여기서는 처리하지 않음
     *
     */
    public User userToEntity() {
        user.setPhoneNumber(user.getPhoneNumber());
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setCreateDate(user.getCreateDate());

        return user;
    }
}
