package com.example.demospringboot4.infraestructure.config;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demospringboot4.infraestructure.dto.ErrorDTO;
import com.example.demospringboot4.infraestructure.exception.ApplicationEntityNotFound;
import com.example.demospringboot4.infraestructure.exception.ApplicationException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorDTO> handleHttpClientErrorException(HttpClientErrorException ex) {
        log.error("Erro ao chamar serviço externo: {}", ex.getMessage(), ex);
        ErrorDTO error = new ErrorDTO(
            List.of(ex.getLocalizedMessage()),
            LocalDateTime.now()
        );
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorDTO> handleApplicationException(ApplicationException ex) {
        log.error("Application exception: {}", ex.getMessage(), ex);
        ErrorDTO error = new ErrorDTO(
            List.of(ex.getLocalizedMessage()),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


    @ExceptionHandler(ApplicationEntityNotFound.class)
    public ResponseEntity<ErrorDTO> handleApplicationEntityNotFound(ApplicationEntityNotFound ex) {
        log.error("Application entity not found: {}", ex.getMessage(), ex);
        ErrorDTO error = new ErrorDTO(
            List.of(ex.getLocalizedMessage()),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("Erro de vali8dação: {}", ex.getMessage(), ex);
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}