package com.example.demospringboot4.ibge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.demospringboot4.ibge.service.IbgeLocalidadesService;

@Configuration
class IbgeHttpClientConfig {

    @Value("${ibge.base.url}")
    private String ibgeBaseUrl;

    @Bean
    IbgeLocalidadesService ibgeLocalidadesService() {

        var restClient =  RestClient.builder()
                .baseUrl(ibgeBaseUrl)
                .build();

        var httpServiceProxyFactory =HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(restClient))
                .build();                
        
        return httpServiceProxyFactory.createClient(IbgeLocalidadesService.class);
    }

}
