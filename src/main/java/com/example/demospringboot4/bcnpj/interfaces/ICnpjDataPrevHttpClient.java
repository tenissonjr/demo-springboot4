package com.example.demospringboot4.bcnpj.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.example.demospringboot4.bcnpj.dto.ConsultaCnpjDataPrevResponseDTO;

@HttpExchange("/api/v1/cnpj")
public interface ICnpjDataPrevHttpClient {

    @GetExchange("/{cnpj}")
    ConsultaCnpjDataPrevResponseDTO consultarCnpjDataPrev(@PathVariable String cnpj);
}
