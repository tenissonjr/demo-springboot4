package com.example.demospringboot4.ibge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demospringboot4.ibge.dto.IbgeEstadoResponseDTO;
import com.example.demospringboot4.ibge.dto.IbgePaisResponseDTO;
import com.example.demospringboot4.ibge.interfaces.IIbgeLocalidadesService;

/**
 * Controlador REST para acessar os dados de localidades fornecidos pela API do IBGE.
 * Disponibiliza endpoints para listar países e estados.
 */
@RestController
@RequestMapping("/api/ibge")
class IbgeLocalidadesController {

    private final IIbgeLocalidadesService ibgeLocalidadesService;

    /**
     * Construtor para injetar o serviço de localidades do IBGE.
     * 
     * @param ibgeLocalidadesService Serviço responsável por acessar os dados do IBGE.
     */
    IbgeLocalidadesController(IIbgeLocalidadesService ibgeLocalidadesService) {
        this.ibgeLocalidadesService = ibgeLocalidadesService;
    }

    /**
     * Endpoint para listar os países disponíveis na API do IBGE.
     * 
     * @return Lista de países.
     */
    @GetMapping("/paises")
    List<IbgePaisResponseDTO> listarPaises() {
        return ibgeLocalidadesService.listarPaises();
    }

    /**
     * Endpoint para listar os estados disponíveis na API do IBGE.
     * 
     * @return Lista de estados.
     */
    @GetMapping("/estados")
    List<IbgeEstadoResponseDTO> listarEstados() {
        return ibgeLocalidadesService.listarEstados();
    }   
}
