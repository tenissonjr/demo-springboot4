package br.gel.casa.consultarfb.cpf.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import br.gel.casa.consultarfb.cpf.dto.ConsultaDataPrevCpfResponseDTO;

@HttpExchange("/api/v1/pessoaFisica")
public interface IDataPrevCpfHttpClient {

    @GetExchange("/{cpf}")
    ConsultaDataPrevCpfResponseDTO consultarCpfDataPrev(@PathVariable String cpf);
    
}