package com.example.demospringboot4.ibge;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ibge/countries")
class IbgeLocalidadesController {

    private final IbgeLocalidadesService ibgeLocalidadesService;

    public IbgeLocalidadesController(IbgeLocalidadesService ibgeLocalidadesService) {
        this.ibgeLocalidadesService = ibgeLocalidadesService;
    }

    @GetMapping
    public List<IbgePaisResponse> listarPaises() {
        return ibgeLocalidadesService.listarPaises();
    }
}
