package com.example.demospringboot4.ibge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa a resposta de um país retornado pela API do IBGE.
 * Contém informações como identificadores e nome do país.
 */
public record IbgePaisResponseDTO(IdDTO id, String nome) {

    /**
     * Representa os identificadores de um país, incluindo M49, ISO-ALPHA-2 e ISO-ALPHA-3.
     */
    public record IdDTO(int M49, @JsonProperty("ISO-ALPHA-2") String ISO_ALPHA_2, @JsonProperty("ISO-ALPHA-3") String ISO_ALPHA_3) {}
}

