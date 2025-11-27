package com.example.demospringboot4.infraestructure.dto;

import java.time.LocalDateTime;

public record ErrorDTO(String message, LocalDateTime timestamp) {

}
