package com.example.demospringboot4.bcnpj.config;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.demospringboot4.bcnpj.BCnpjService;

@Configuration
class BCnpjHttpClientConfig {

    @Value("${bcnpj.base.url}")
    private String bcnpjBaseUrl;

    private String encodeBasic(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }


    @Bean
    BCnpjService bCnpjService() {

        String username = "bcnpj"; // Replace with your actual username
        String password = "app#2025*CD"; // Replace with your actual password


        var restClient =  RestClient.builder()
                .baseUrl(bcnpjBaseUrl)
                 .defaultHeader(HttpHeaders.AUTHORIZATION, encodeBasic(username, password))
                .build();

        var httpServiceProxyFactory =HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(restClient))
                .build();                
        
        return httpServiceProxyFactory.createClient(BCnpjService.class);
    }

}
