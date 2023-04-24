package ru.kata.spring.boot_security.demo.response.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.kata.spring.boot_security.demo.response.constant.Code;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private Code code;
    private String userMessage;
    private String techMessage;
}