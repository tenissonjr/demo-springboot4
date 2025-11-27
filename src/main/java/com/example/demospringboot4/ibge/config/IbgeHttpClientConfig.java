package com.example.demospringboot4.ibge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.demospringboot4.ibge.interfaces.IIbgeLocalidadesService;

@Configuration
class IbgeHttpClientConfig {

    @Value("${ibge.base.url}")
    private String ibgeBaseUrl;

    @Bean
    IIbgeLocalidadesService ibgeLocalidadesService() {
        //Configura o RestClient com a URL base da API do IBGE
        var restClient =  RestClient.builder()
                .baseUrl(ibgeBaseUrl)
                .build();

        //Retorna objeto criado pelo StringBoot que implementa a interface IIbgeLocalidadesService        
        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(restClient))
                .build()
                .createClient(IIbgeLocalidadesService.class);
    }

}
