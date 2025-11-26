package com.example.demo_springboot4.ibge;

import java.util.List;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(accept = "application/json")
public interface IbgeCountryClient {

    @GetExchange("/api/v1/localidades/paises")
    List<IbgeCountryResponse> fetchCountries();
}
