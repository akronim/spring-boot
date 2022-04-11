package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

// see UserExceptionHandler.java

@Data
@AllArgsConstructor
public class AppError {
    private String errorCode;
    private String message;
    private HttpStatus httpStatus;
}
