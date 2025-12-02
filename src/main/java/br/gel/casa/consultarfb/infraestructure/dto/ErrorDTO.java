package br.gel.casa.consultarfb.infraestructure.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorDTO(List<String> messages, LocalDateTime timestamp) {

}
