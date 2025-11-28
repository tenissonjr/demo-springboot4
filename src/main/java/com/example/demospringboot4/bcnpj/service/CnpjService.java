package com.example.demospringboot4.bcnpj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Profile;

import com.example.demospringboot4.bcnpj.dto.ConsultaCnpjDataPrevResponseDTO;
import com.example.demospringboot4.bcnpj.dto.ConsultaCnpjResponseDTO;
import com.example.demospringboot4.bcnpj.interfaces.ICnpjDataPrevHttpClient;
import com.example.demospringboot4.bcnpj.interfaces.ICnpjService;

@Profile("prd")
@Service
public class CnpjService implements ICnpjService {
    
    private final Logger log = LoggerFactory.getLogger(CnpjService.class);

     private final ICnpjDataPrevHttpClient bCnpjHttpClient;

    public CnpjService(ICnpjDataPrevHttpClient bCnpjHttpClient) {
        this.bCnpjHttpClient = bCnpjHttpClient;
    }

    public ConsultaCnpjDataPrevResponseDTO consultarCnpjOriginal(String cnpj) {
        log.info("Consultando dados originais CNPJ: {}", cnpj);
        return bCnpjHttpClient.consultarCnpjDataPrev(cnpj);
    }

    @Override
    public ConsultaCnpjResponseDTO consultarCnpj(String cnpj) {
        log.info("Transformando dados  do  CNPJ: {}", cnpj);
        return ConsultaCnpjResponseDTO.valueOf(consultarCnpjOriginal(cnpj));
    }



    
}