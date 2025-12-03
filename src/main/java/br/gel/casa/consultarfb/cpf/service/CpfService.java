package br.gel.casa.consultarfb.cpf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.gel.casa.consultarfb.cpf.dto.ConsultaCpfResponseDTO;
import br.gel.casa.consultarfb.cpf.interfaces.IDataPrevCpfHttpClient;
import br.gel.casa.consultarfb.cpf.validation.CpfValido;

@Service
@Validated
public class CpfService  {
    
    private static final Logger log = LoggerFactory.getLogger(CpfService.class);

     private final IDataPrevCpfHttpClient dataPrevCpfHttpClient;

    public CpfService(IDataPrevCpfHttpClient bCpfHttpClient) {
        this.dataPrevCpfHttpClient = bCpfHttpClient;
    }

    public ConsultaCpfResponseDTO consultarCpf(@CpfValido String cpf) {
        log.info("Transformando dados do CPF: {}", cpf);
        return ConsultaCpfResponseDTO.valueOf(this.dataPrevCpfHttpClient.consultarCpfDataPrev(cpf),"1");
    }
}