package com.example.demospringboot4.bcnpj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demospringboot4.bcnpj.dto.ConsultaCnpjResponseDTO;
import com.example.demospringboot4.bcnpj.dto.ConsultaDataPrevCnpjResponseDTO;
import com.example.demospringboot4.bcnpj.interfaces.IDataPrevCnpjHttpClient;
import com.example.demospringboot4.bcnpj.views.CnpjViews;
import com.example.demospringboot4.bcnpj.validation.CnpjValido;
import com.fasterxml.jackson.annotation.JsonView;


@Service
@Validated
public class CnpjService  {
    
    private static final Logger log = LoggerFactory.getLogger(CnpjService.class);

     private final IDataPrevCnpjHttpClient bCnpjHttpClient;

    public CnpjService(IDataPrevCnpjHttpClient bCnpjHttpClient) {
        this.bCnpjHttpClient = bCnpjHttpClient;
    }

    @JsonView(CnpjViews.Basico.class)
    public ConsultaCnpjResponseDTO consultarCnpj(@CnpjValido String cnpj) {
        log.info("Transformando dados do  CNPJ: {}", cnpj);
        return ConsultaCnpjResponseDTO.valueOf(this.bCnpjHttpClient.consultarCnpjDataPrev(cnpj));
    }




    
}