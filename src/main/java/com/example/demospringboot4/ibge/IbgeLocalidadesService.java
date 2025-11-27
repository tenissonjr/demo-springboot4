package com.example.demospringboot4.ibge;

import java.util.List;

import org.springframework.resilience.annotation.Retryable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;


// URL Base: https://servicodados.ibge.gov.br
// Recurso: /api/v1/localidades
// Endpoint: /paises

@HttpExchange("/api/v1/localidades")
public interface IbgeLocalidadesService {

    @GetExchange("/paises")
   

    List<IbgePaisResponse> listarPaises();
}
