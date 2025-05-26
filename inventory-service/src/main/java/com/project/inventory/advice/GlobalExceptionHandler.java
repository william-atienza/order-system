package com.project.inventory.advice;

import com.project.inventory.exception.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(Exception e) {
        return e.getMessage();
    }
}
