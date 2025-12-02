package br.leg.casa.bcnpj.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import br.leg.casa.bcnpj.dto.ConsultaDataPrevCnpjResponseDTO;

@HttpExchange("/api/v1/cnpj")
public interface IDataPrevCnpjHttpClient {

    @GetExchange("/{cnpj}")
    ConsultaDataPrevCnpjResponseDTO consultarCnpjDataPrev(@PathVariable String cnpj);
}
