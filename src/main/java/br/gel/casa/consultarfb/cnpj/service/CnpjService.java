package br.gel.casa.consultarfb.cnpj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.resilience.annotation.Retryable;
import br.gel.casa.consultarfb.cnpj.dto.ConsultaCnpjResponseDTO;
import br.gel.casa.consultarfb.cnpj.interfaces.IDataPrevCnpjHttpClient;
import br.gel.casa.consultarfb.cnpj.validation.CnpjValido;


@Service
@Validated
public class CnpjService  {
    
    private static final Logger log = LoggerFactory.getLogger(CnpjService.class);

     private final IDataPrevCnpjHttpClient bCnpjHttpClient;

    public CnpjService(IDataPrevCnpjHttpClient bCnpjHttpClient) {
        this.bCnpjHttpClient = bCnpjHttpClient;
    }

    @Retryable(
            maxRetries= 4,
            delay = 1000, // 1-second delay
            multiplier = 2 // double the delay for each retry attempt
    )
    public ConsultaCnpjResponseDTO consultarCnpj(@CnpjValido String cnpj) {
        log.info("Transformando dados do  CNPJ: {}", cnpj);
        return ConsultaCnpjResponseDTO.valueOf(this.bCnpjHttpClient.consultarCnpjDataPrev(cnpj));
    }




    
}