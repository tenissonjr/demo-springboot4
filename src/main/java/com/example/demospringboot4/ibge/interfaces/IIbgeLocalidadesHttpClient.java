package com.example.demospringboot4.ibge.interfaces;

import java.util.List;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.example.demospringboot4.ibge.dto.IbgeEstadoResponseDTO;
import com.example.demospringboot4.ibge.dto.IbgePaisResponseDTO;

/**
 * Interface para comunicação com os endpoints de localidades da API do IBGE.
 * Disponibiliza métodos para listar países e estados.
 */
@HttpExchange("/api/v1/localidades")
public interface IIbgeLocalidadesHttpClient {

    /**
     * Recupera a lista de países disponíveis na API do IBGE.
     *
     * @return Lista de países.
     */
    @GetExchange("/paises")
    List<IbgePaisResponseDTO> listarPaises();

    /**
     * Recupera a lista de estados disponíveis na API do IBGE.
     *
     * @return Lista de estados.
     */
    @GetExchange("/estados")
    List<IbgeEstadoResponseDTO> listarEstados();

}
