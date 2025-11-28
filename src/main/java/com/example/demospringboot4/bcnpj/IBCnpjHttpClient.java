package com.example.demospringboot4.bcnpj;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.example.demospringboot4.bcnpj.dto.BCnpjResponseDTO;

@HttpExchange("/api/v1/cnpj")
public interface IBCnpjHttpClient {

    @GetExchange("/{cnpj}")
    BCnpjResponseDTO consultarCnpj(@PathVariable String cnpj);
}
