package com.example.demospringboot4.ibge.dto;
/**
 * Representa a resposta de um estado retornado pela API do IBGE.
 * Contém informações básicas como id, sigla e nome do estado.
 */
public record IbgeEstadoResponseDTO(int id, String sigla, String nome) {}