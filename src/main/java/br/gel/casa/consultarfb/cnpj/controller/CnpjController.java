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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cnpj")
@Validated
@ConcurrencyLimit(50)
@Tag(name = "CNPJ", description = "Endpoints para operações relacionadas a CNPJ")
public class CnpjController {

    private static final Logger log = LoggerFactory.getLogger(CnpjController.class);

    private final CnpjService cnpjService;

    public CnpjController(CnpjService cnpjService) {
        this.cnpjService = cnpjService;
    }

    @Operation(summary = "Consulta básica de CNPJ", description = "Retorna dados básicos do CNPJ informado.")
    @GetMapping("/{cnpj}/basico")
    @JsonView(CnpjViews.Basico.class)    
    public ResponseEntity<ConsultaCnpjResponseDTO> consultarCnpjBasico(@PathVariable @CnpjValido String cnpj) {
        log.info("Delegando a consulta de dados básicos do  CNPJ: {}", cnpj);
        return ResponseEntity.ok(cnpjService.consultarCnpj(cnpj));
    }

    @Operation(summary = "Consulta completa de CNPJ", description = "Retorna dados completos do CNPJ informado.")
    @GetMapping("/{cnpj}/completo")
    @JsonView(CnpjViews.Completo.class)
    public ResponseEntity<ConsultaCnpjResponseDTO> consultarCnpjCompleto(@PathVariable @CnpjValido String cnpj) {
        log.info("Delegando a consulta de dados completos do CNPJ: {}", cnpj);
        return ResponseEntity.ok(cnpjService.consultarCnpj(cnpj));
    }

}