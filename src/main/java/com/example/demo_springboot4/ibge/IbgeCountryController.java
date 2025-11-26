package com.example.demo_springboot4.ibge;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ibge/countries")
class IbgeCountryController {

    private final IbgeCountryClient countryClient;

    public IbgeCountryController(IbgeCountryClient countryClient) {
        this.countryClient = countryClient;
    }

    @GetMapping
    public List<IbgeCountryResponse> listCountries() {
        return countryClient.fetchCountries();
    }
}
