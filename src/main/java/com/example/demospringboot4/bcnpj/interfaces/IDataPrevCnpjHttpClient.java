package com.example.demospringboot4.bcnpj.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.example.demospringboot4.bcnpj.dto.ConsultaDataPrevCnpjResponseDTO;

@HttpExchange("/api/v1/cnpj")
public interface IDataPrevCnpjHttpClient {

    @GetExchange("/{cnpj}")
    ConsultaDataPrevCnpjResponseDTO consultarCnpjDataPrev(@PathVariable String cnpj);
}
