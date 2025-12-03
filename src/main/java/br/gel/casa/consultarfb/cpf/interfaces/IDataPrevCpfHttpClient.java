package br.gel.casa.consultarfb.cpf.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import br.gel.casa.consultarfb.cpf.dto.ConsultaDataPrevCpfResponseDTO;
import br.gel.casa.consultarfb.infraestructure.exception.ApplicationEntityNotFound;
import br.gel.casa.consultarfb.infraestructure.exception.ApplicationException;

@HttpExchange("/api/v1/pessoaFisica")
public interface IDataPrevCpfHttpClient {

    @GetExchange("/{cpf}")
    ConsultaDataPrevCpfResponseDTO consultarCpfDataPrev(@PathVariable String cpf);

  public default ConsultaDataPrevCpfResponseDTO consultarCpfDataPrevWithErrorHandling(String cpf) {
        try {
            return consultarCpfDataPrev(cpf);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ApplicationEntityNotFound("Usuário com  " + cpf + " não encontrado.");
        } catch (HttpClientErrorException e) {
            throw new ApplicationException(e.getMessage());
        }
    }    
}