package com.example.demospringboot4.bcnpj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demospringboot4.bcnpj.BCnpjService;
import com.example.demospringboot4.bcnpj.dto.BCnpjResponseDTO;




@RestController
@RequestMapping("/api/cnpj")
public class BCnpjController {

    private final BCnpjService bCnpjService;

    public BCnpjController(BCnpjService bCnpjService) {
        this.bCnpjService = bCnpjService;
    }

    @GetMapping("/{cnpj}")
    public BCnpjResponseDTO getCnpj(@PathVariable String cnpj) {
        return bCnpjService.consultarCnpj(cnpj);
    }
}