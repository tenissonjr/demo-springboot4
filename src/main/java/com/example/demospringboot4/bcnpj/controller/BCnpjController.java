package com.example.demospringboot4.bcnpj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demospringboot4.bcnpj.IBCnpjHttpClient;
import com.example.demospringboot4.bcnpj.dto.BCnpjResponseDTO;
import com.example.demospringboot4.bcnpj.dto.ConsultaRfbCnpjResponseDTO;
import com.example.demospringboot4.bcnpj.views.CnpjViews;
import com.fasterxml.jackson.annotation.JsonView;




@RestController
@RequestMapping("/api/cnpj")
@ConcurrencyLimit(value = 5)
public class BCnpjController {

    private final Logger log = LoggerFactory.getLogger(BCnpjController.class);

    private final IBCnpjHttpClient bCnpjHttpClient;

    public BCnpjController(IBCnpjHttpClient bCnpjService) {
        this.bCnpjHttpClient = bCnpjService;
    }

    @GetMapping("/{cnpj}/original")
    public BCnpjResponseDTO consultarCnpjOriginal(@PathVariable String cnpj) {
        log.info("Consultando dados originais CNPJ: {}", cnpj);
        return bCnpjHttpClient.consultarCnpj(cnpj);
    }

    @GetMapping("/{cnpj}/basico")
    @JsonView(CnpjViews.Basico.class)
    public ConsultaRfbCnpjResponseDTO consultarCnpjBasico(@PathVariable String cnpj) {
        log.info("Consultando dados básicos do  CNPJ: {}", cnpj);
        return ConsultaRfbCnpjResponseDTO.valueOf(bCnpjHttpClient.consultarCnpj(cnpj));
    }

    @GetMapping("/{cnpj}/completo")
    @JsonView(CnpjViews.Completo.class)
    public ConsultaRfbCnpjResponseDTO consultarCnpjCompleto(@PathVariable String cnpj) {
        log.info("Consultando dados completos do CNPJ: {}", cnpj);
        return ConsultaRfbCnpjResponseDTO.valueOf(bCnpjHttpClient.consultarCnpj(cnpj));
    }


}