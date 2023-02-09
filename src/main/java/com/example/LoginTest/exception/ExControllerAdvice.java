package com.example.LoginTest.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @ControllerAdvice는 대상으로 지정한 여러 컨트롤러에 @ExceptionHandler , @InitBinder 기능을
 * 부여해주는 역할을 해줍니다. 패키지를 지정하거나, 특정 애노테이션을 타겟으로 정할 수 있습니다.
 * @ExceptionHandler에 예외를 생략할 수 있습니다. 생략하면 메서드 파라미터의 예외가 지정됩니다.
 * ResponseEntity를 반환하면 HTTP 응답 코드를 동적으로 변경할 수 있습니다.
 * @ResponseStatus 애노테이션으로는 HTTP 응답 코드를 동적으로 변경할 수 없습니다.
 */
//@RestControllerAdvice
@Slf4j
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalAccessError.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[ExceptionHandler] ex", e);
        return new ErrorResult("Bad", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userHandler(UserException e) {
        log.error("[ExceptionHandler] ex ", e);
        ErrorResult userEx = new ErrorResult("USER_EX", e.getMessage());
        return new ResponseEntity<>(userEx, HttpStatus.BAD_REQUEST);
    }

    public ErrorResult exHandler(Exception e) {
        log.error("[ExceptionHandler] ex", e);
        return new ErrorResult("Ex","내보 오류");
    }

}
