package com.example.demospringboot4.bcnpj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demospringboot4.bcnpj.dto.ConsultaCnpjResponseDTO;
import com.example.demospringboot4.bcnpj.service.CnpjService;
import com.example.demospringboot4.bcnpj.validation.CnpjValido;
import com.example.demospringboot4.bcnpj.views.CnpjViews;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/cnpj")
@ConcurrencyLimit(value = 5)
@Validated
public class BCnpjController {

    private static final Logger log = LoggerFactory.getLogger(BCnpjController.class);

    private final CnpjService cnpjService;

    public BCnpjController(CnpjService cnpjService) {
        this.cnpjService = cnpjService;
    }

    @GetMapping("/{cnpj}/basico")
    @JsonView(CnpjViews.Basico.class)    
    public ConsultaCnpjResponseDTO consultarCnpjBasico(@PathVariable @CnpjValido String cnpj) {
        log.info("Delegando a consulta de dados básicos do  CNPJ: {}", cnpj);
        return cnpjService.consultarCnpj(cnpj);
    }

    @GetMapping("/{cnpj}/completo")
    @JsonView(CnpjViews.Completo.class)
    public ConsultaCnpjResponseDTO consultarCnpjCompleto(@PathVariable @CnpjValido String cnpj) {
        log.info("Delegando a consulta de dados completos do CNPJ: {}", cnpj);
        return cnpjService.consultarCnpj(cnpj);
    }

}