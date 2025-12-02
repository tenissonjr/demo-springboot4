package br.gel.casa.consultarfb.cnpj.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import br.gel.casa.consultarfb.cnpj.dto.ConsultaDataPrevCnpjResponseDTO;

@HttpExchange("/api/v1/cnpj")
public interface IDataPrevCnpjHttpClient {

    @GetExchange("/{cnpj}")
    ConsultaDataPrevCnpjResponseDTO consultarCnpjDataPrev(@PathVariable String cnpj);
}
