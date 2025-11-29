package com.example.demospringboot4.bcnpj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demospringboot4.bcnpj.dto.ConsultaCnpjResponseDTO;
import com.example.demospringboot4.bcnpj.service.CnpjService;

@RestController
@RequestMapping("/api/cnpj")
@ConcurrencyLimit(value = 5)
public class BCnpjController {

    private final Logger log = LoggerFactory.getLogger(BCnpjController.class);

    private final CnpjService cnpjService;

    public BCnpjController(CnpjService cnpjService) {
        this.cnpjService = cnpjService;
    }

    @GetMapping("/{cnpj}/basico")
    public ConsultaCnpjResponseDTO consultarCnpjBasico(@PathVariable String cnpj) {
        log.info("Delegando a consulta de dados básicos do  CNPJ: {}", cnpj);
        return cnpjService.consultarCnpjBasico(cnpj);
    }

    @GetMapping("/{cnpj}/completo")
    public ConsultaCnpjResponseDTO consultarCnpjCompleto(@PathVariable String cnpj) {
        log.info("Delegando a consulta de dados completos do CNPJ: {}", cnpj);
        return cnpjService.consultarCnpjCompleto(cnpj);
    }

}