package com.example.demospringboot4.bcnpj.config;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.demospringboot4.bcnpj.IBCnpjHttpClient;

@Configuration
class BCnpjHttpClientConfig {

    @Value("${bcnpj.base.url}")
    private String bcnpjBaseUrl;

    @Value("${bcnpj.credentials.username}")
    private String username;

    @Value("${bcnpj.credentials.password}")
    private String password;

    private String encodeBasic(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }


    @Bean
    IBCnpjHttpClient bCnpjHttpClient() {

        // Configura o RestClient com a URL base da API do BCnpj
        var restClient = RestClient.builder()
                .baseUrl(bcnpjBaseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, encodeBasic(username, password))
                .build();

        // Retorna um objeto criado pelo Spring Boot que implementa a interface IBCnpjService
        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(restClient))
                .build()
                .createClient(IBCnpjHttpClient.class);
    }

}
