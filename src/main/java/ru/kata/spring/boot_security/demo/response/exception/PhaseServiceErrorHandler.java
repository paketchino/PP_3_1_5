package ru.kata.spring.boot_security.demo.response.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.kata.spring.boot_security.demo.response.constant.Code;
import ru.kata.spring.boot_security.demo.response.error.Error;
import ru.kata.spring.boot_security.demo.response.error.ErrorResponse;
import java.util.NoSuchElementException;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class PhaseServiceErrorHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleCommonException(CommonException exception) {
        log.error("MethodArgumentTypeMismatchException: {}", exception.toString());
        return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder()
                .code(exception.getCode())
                .userMessage(exception.getUserMessage())
                .techMessage(exception.getTechMessage())
                .build()).build(), exception.getHttpStatus());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchExceptionNoSuch(MethodArgumentTypeMismatchException ex) {
        log.error("NoSuchElementException: {}", ex.toString());
        return new ResponseEntity<>(ErrorResponse.builder()
                .error(Error.builder().code(Code.NO_SUCH_ELEMENT_EXCEPTION).techMessage(ex.getMessage()).build()).build(), NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchExceptionUserNotFound(MethodArgumentTypeMismatchException ex) {
        log.error("MethodArgumentTypeMismatchException: {}", ex.toString());
        return new ResponseEntity<>(ErrorResponse.builder()
                .error(Error.builder().code(Code.USER_NOT_FOUND_EXCEPTION).techMessage(ex.getMessage()).build()).build(), NOT_FOUND);
    }
}
