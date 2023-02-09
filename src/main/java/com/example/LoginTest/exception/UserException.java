package com.example.LoginTest.exception;

/**
 * 사용자 예외처리를 위한 클래스
 */
public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
