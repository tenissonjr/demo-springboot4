package br.gel.casa.consultarfb.cnpj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gel.casa.consultarfb.cnpj.dto.ConsultaCnpjResponseDTO;
import br.gel.casa.consultarfb.cnpj.service.CnpjService;
import br.gel.casa.consultarfb.cnpj.validation.CnpjValido;
import br.gel.casa.consultarfb.cnpj.views.CnpjViews;

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
    public ResponseEntity<ConsultaCnpjResponseDTO> consultarCnpjBasico(@PathVariable @CnpjValido String cnpj) {
        log.info("Delegando a consulta de dados básicos do  CNPJ: {}", cnpj);
        return ResponseEntity.ok(cnpjService.consultarCnpj(cnpj));
    }

    @GetMapping("/{cnpj}/completo")
    @JsonView(CnpjViews.Completo.class)
    public ResponseEntity<ConsultaCnpjResponseDTO> consultarCnpjCompleto(@PathVariable @CnpjValido String cnpj) {
        log.info("Delegando a consulta de dados completos do CNPJ: {}", cnpj);
        return ResponseEntity.ok(cnpjService.consultarCnpj(cnpj));
    }

}