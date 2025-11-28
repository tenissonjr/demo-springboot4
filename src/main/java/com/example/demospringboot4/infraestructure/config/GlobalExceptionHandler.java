package com.example.demospringboot4.infraestructure.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demospringboot4.infraestructure.dto.ErrorDTO;
import com.example.demospringboot4.infraestructure.exception.ApplicationEntityNotFound;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorDTO> handleHttpClientErrorException(HttpClientErrorException ex) {

        ErrorDTO error = new ErrorDTO(
            ex.getLocalizedMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.valueOf(ex.getStatusCode().value()));
    }

    @ExceptionHandler(ApplicationEntityNotFound.class)
    public ResponseEntity<ErrorDTO> handleApplicationEntityNotFound(ApplicationEntityNotFound ex) {
        ErrorDTO error = new ErrorDTO(
            ex.getLocalizedMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

}   