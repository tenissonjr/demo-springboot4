package com.example.demospringboot4.infraestructure.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorDTO(List<String> messages, LocalDateTime timestamp) {

}
