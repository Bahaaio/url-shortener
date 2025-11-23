package com.github.bahaaio.urlshortener.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleBadRequest(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(fieldError -> Map.of(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }
}
