package com.example.demospringboot4.infraestructure.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demospringboot4.infraestructure.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private String extractErrorMessage(String errorString) {
        if (errorString == null || errorString.isEmpty()) {
            return "Mensagem de erro não disponível.";
        }

        int startIndex = errorString.indexOf(':');
        if (startIndex != -1 && startIndex + 1 < errorString.length()) {
            String extractedMessage = errorString.substring(startIndex + 1).trim();
            return extractedMessage.replace("\"", ""); // Remove aspas duplas
        }

        return errorString.replace("\"", ""); // Remove aspas duplas
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorDTO> handleHttpClientErrorException(HttpClientErrorException ex) {

        ErrorDTO error = new ErrorDTO(
            extractErrorMessage(ex.getMessage()),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.valueOf(ex.getStatusCode().value()));
    }

}
