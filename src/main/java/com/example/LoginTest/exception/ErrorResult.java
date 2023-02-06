package com.example.LoginTest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  ExceptionHandlerExceptionResolver
 *  일관된 에러 메서지 형식을 응답하기 위한 클래스
 */
@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;
}
