package br.gel.casa.consultarfb.cpf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.gel.casa.consultarfb.cpf.dto.ConsultaCpfResponseDTO;
import br.gel.casa.consultarfb.cpf.interfaces.IDataPrevCpfHttpClient;
import br.gel.casa.consultarfb.cpf.validation.CpfValido;
import br.gel.casa.consultarfb.infraestructure.exception.ApplicationEntityNotFound;

@Service
@Validated
public class CpfService  {
    
    private static final Logger log = LoggerFactory.getLogger(CpfService.class);

     private final IDataPrevCpfHttpClient dataPrevCpfHttpClient;

    public CpfService(IDataPrevCpfHttpClient bCpfHttpClient) {
        this.dataPrevCpfHttpClient = bCpfHttpClient;
    }

    @Retryable(
            excludes = ApplicationEntityNotFound.class,
            maxRetries= 4,
            delay = 1000, // 1-second delay
            multiplier = 2 // double the delay for each retry attempt
    )    
    public ConsultaCpfResponseDTO consultarCpf(@CpfValido String cpf) {
        log.info("Transformando dados do CPF: {}", cpf);
        var response = this.dataPrevCpfHttpClient.consultarCpfDataPrev(cpf);
        if(response == null) {
            throw new ApplicationEntityNotFound("Usuário com CPF " + cpf + " não encontrado.");
        }
        return ConsultaCpfResponseDTO.valueOf(response,"1");
    }
}