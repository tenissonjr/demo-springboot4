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

@RestController
@RequestMapping("/api/cpf")
@Validated
@ConcurrencyLimit(50)
public class CpfController {

    private static final Logger log = LoggerFactory.getLogger(CpfController.class);

    private final CpfService cpfService;

    public CpfController(CpfService cpfService) {
        this.cpfService = cpfService;
    }

    @GetMapping("/{cpf}/basico")
    @JsonView(CpfViews.Basico.class)    
    public ResponseEntity<ConsultaCpfResponseDTO> consultarCpfBasico(@PathVariable @CpfValido String cpf) {
        log.info("Delegando a consulta de dados básicos do CPF: {}", cpf);
        return ResponseEntity.ok(cpfService.consultarCpf(cpf));
    }

    @GetMapping("/{cpf}/complemento")
    @JsonView(CpfViews.Complemento.class)
    public ResponseEntity<ConsultaCpfResponseDTO> consultarCpfComplemento(@PathVariable @CpfValido String cpf) {
        log.info("Delegando a consulta de dados completos do CPF: {}", cpf);
        return ResponseEntity.ok(cpfService.consultarCpf(cpf));
    }


    @GetMapping("/{cpf}/completo")
    @JsonView(CpfViews.Completo.class)
    public ResponseEntity<ConsultaCpfResponseDTO> consultarCpfCompleto(@PathVariable @CpfValido String cpf) {
        log.info("Delegando a consulta de dados completos do CPF: {}", cpf);
        return ResponseEntity.ok(cpfService.consultarCpf(cpf));
    }

}