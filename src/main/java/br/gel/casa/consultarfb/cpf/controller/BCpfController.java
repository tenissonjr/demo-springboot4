package br.gel.casa.consultarfb.cpf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@ConcurrencyLimit(value = 5)
@Validated
public class BCpfController {

    private static final Logger log = LoggerFactory.getLogger(BCpfController.class);

    private final CpfService cpfService;

    public BCpfController(CpfService cpfService) {
        this.cpfService = cpfService;
    }

    @GetMapping("/{cpf}/basico")
    @JsonView(CpfViews.Basico.class)    
    public ConsultaCpfResponseDTO consultarCpfBasico(@PathVariable @CpfValido String cpf) {
        log.info("Delegando a consulta de dados básicos do CPF: {}", cpf);
        return cpfService.consultarCpf(cpf);
    }

    @GetMapping("/{cpf}/complemento")
    @JsonView(CpfViews.Complemento.class)
    public ConsultaCpfResponseDTO consultarCpfComplemento(@PathVariable @CpfValido String cpf) {
        log.info("Delegando a consulta de dados completos do CPF: {}", cpf);
        return cpfService.consultarCpf(cpf);
    }


    @GetMapping("/{cpf}/completo")
    @JsonView(CpfViews.Completo.class)
    public ConsultaCpfResponseDTO consultarCpfCompleto(@PathVariable @CpfValido String cpf) {
        log.info("Delegando a consulta de dados completos do CPF: {}", cpf);
        return cpfService.consultarCpf(cpf);
    }

}