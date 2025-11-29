package com.example.demospringboot4.infraestructure.config;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demospringboot4.infraestructure.dto.ErrorDTO;
import com.example.demospringboot4.infraestructure.exception.ApplicationResourceNotFound;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorDTO> handleHttpClientErrorException(HttpClientErrorException ex) {

        ErrorDTO error = new ErrorDTO(
            List.of(ex.getLocalizedMessage()),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.valueOf(ex.getStatusCode().value()));
    }

    @ExceptionHandler(ApplicationResourceNotFound.class)
    public ResponseEntity<ErrorDTO> handleApplicationEntityNotFound(ApplicationResourceNotFound ex) {
        ErrorDTO error = new ErrorDTO(
            List.of(ex.getLocalizedMessage()),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> messages = ex.getConstraintViolations()
            .stream()
            .map(violation -> violation.getMessage())
            .collect(Collectors.toList());

        if (messages.isEmpty()) {
            messages = List.of(ex.getLocalizedMessage());
        }

        ErrorDTO error = new ErrorDTO(
            messages,
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}   