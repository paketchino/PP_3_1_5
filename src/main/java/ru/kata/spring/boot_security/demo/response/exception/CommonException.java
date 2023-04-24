package ru.kata.spring.boot_security.demo.response.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import ru.kata.spring.boot_security.demo.response.constant.Code;

@Data
@Builder
public class CommonException extends RuntimeException {

    private final Code code;
    private final String userMessage;
    private final String techMessage;
    private final HttpStatus httpStatus;
}
