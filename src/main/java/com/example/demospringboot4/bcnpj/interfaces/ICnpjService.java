package com.example.demospringboot4.bcnpj.interfaces;

import com.example.demospringboot4.bcnpj.dto.ConsultaCnpjResponseDTO;

public interface ICnpjService {
    ConsultaCnpjResponseDTO consultarCnpj(String cnpj);
}