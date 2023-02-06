package com.example.LoginTest.exception;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//TODO : 전역 예외처리 클래스
// https://mangkyu.tistory.com/204
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(NoSuchElementFoundException.class)
//    protected ResponseEntity<?> handleNoSuchElementFoundException(NoSuchElementFoundException e) {
//        final ErrorResponse errorResponse = ErrorResponse.builder()
//                .code("Item Not Found")
//                .message(e.getMessage()).build();
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//    }
//
//    private class NoSuchElementFoundException extends Throwable {
//
//    }
//}