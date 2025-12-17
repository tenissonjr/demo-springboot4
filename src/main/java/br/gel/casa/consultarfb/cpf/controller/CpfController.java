package br.gel.casa.consultarfb.cpf.controller;

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

import br.gel.casa.consultarfb.cpf.dto.ConsultaCpfResponseDTO;
import br.gel.casa.consultarfb.cpf.service.CpfService;
import br.gel.casa.consultarfb.cpf.validation.CpfValido;
import br.gel.casa.consultarfb.cpf.views.CpfViews;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cpf")
@Validated
@ConcurrencyLimit(50)
@Tag(name = "CPF", description = "Endpoints para operações relacionadas a CPF")
public class CpfController {

    private static final Logger log = LoggerFactory.getLogger(CpfController.class.getName());

    private final CpfService cpfService;

    public CpfController(CpfService cpfService) {
        this.cpfService = cpfService;
    }

    @Operation(summary = "Consulta básica de CPF", description = "Retorna dados básicos do CPF informado.")
    @GetMapping(value = "/{cpf}/basico")
    @JsonView(CpfViews.Basico.class)    
    public ResponseEntity<ConsultaCpfResponseDTO> consultarCpfBasico(@PathVariable @CpfValido String cpf) {
        log.info("Delegando a consulta de dados básicos do CPF: {}", cpf);
        return ResponseEntity.ok(cpfService.consultarCpf(cpf));
    }

    @Operation(summary = "Consulta complemento de CPF", description = "Retorna dados complementares do CPF informado.")
    @GetMapping(value="/{cpf}/complemento")
    @JsonView(CpfViews.Complemento.class)
    public ResponseEntity<ConsultaCpfResponseDTO> consultarCpfComplemento(@PathVariable @CpfValido String cpf) {
        log.info("Delegando a consulta de dados completos do CPF: {}", cpf);
        return ResponseEntity.ok(cpfService.consultarCpf(cpf));
    }


    @GetMapping( value="/{cpf}/completo", version = "1.0.0")
    @Operation(summary = "Consulta completa de CPF (Versão 1.0.0)", description = "Retorna dados completos do CPF informado. (Versão 1.0.0)")
    @JsonView(CpfViews.Completo.class)
    public ResponseEntity<ConsultaCpfResponseDTO> consultarCpfCompletoVersao1(@PathVariable @CpfValido String cpf) {
        log.warn("Versão 1.0.0 Delegando a consulta de dados completos do CPF: {}", cpf);
        return ResponseEntity.ok(cpfService.consultarCpf(cpf));
    }

    @Operation(summary = "Consulta completa de CPF (Versão 2.0.0)", description = "Retorna dados completos do CPF informado. (Versão 2.0.0)")
    @GetMapping( value="/{cpf}/completo", version = "2.0.0")
    @JsonView(CpfViews.Completo.class)
    public ResponseEntity<ConsultaCpfResponseDTO> consultarCpfCompleto(@PathVariable @CpfValido String cpf) {
        log.info("Delegando a consulta de dados completos do CPF: {}", cpf);
        return ResponseEntity.ok(cpfService.consultarCpf(cpf));
    }


}