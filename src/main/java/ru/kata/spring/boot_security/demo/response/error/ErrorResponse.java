package ru.kata.spring.boot_security.demo.response.error;

import lombok.Builder;
import lombok.Data;
import ru.kata.spring.boot_security.demo.response.Response;

@Data
@Builder
public class ErrorResponse implements Response {

    private Error error;
}