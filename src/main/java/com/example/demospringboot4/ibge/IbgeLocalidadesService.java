package com.example.demospringboot4.ibge;

import java.util.List;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api/v1/localidades")
public interface IbgeLocalidadesService {

    @GetExchange("/paises")
    List<IbgePaisResponse> listarPaises();
}
