package com.example.demospringboot4.bcnpj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demospringboot4.bcnpj.IBCnpjService;
import com.example.demospringboot4.bcnpj.dto.BCnpjResponseDTO;
import com.example.demospringboot4.bcnpj.dto.ConsultaRfbCnpjResponseDTO;




@RestController
@RequestMapping("/api/cnpj")
public class BCnpjController {

    private final IBCnpjService bCnpjService;

    public BCnpjController(IBCnpjService bCnpjService) {
        this.bCnpjService = bCnpjService;
    }

    @GetMapping("/{cnpj}/original")
    public BCnpjResponseDTO consultarCnpjOriginal(@PathVariable String cnpj) {
        return bCnpjService.consultarCnpj(cnpj);
    }

    @GetMapping("/{cnpj}")
    public ConsultaRfbCnpjResponseDTO consultarCnpj(@PathVariable String cnpj) {
        return ConsultaRfbCnpjResponseDTO.valueOf(bCnpjService.consultarCnpj(cnpj));
    }

}