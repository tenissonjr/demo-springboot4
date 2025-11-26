package com.example.demo_springboot4.ibge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration(proxyBeanMethods = false)
public class IbgeHttpClientConfig {

    @Bean
    IbgeCountryClient ibgeCountryClient(RestClient.Builder builder) {
        RestClient restClient = builder
                .baseUrl("https://servicodados.ibge.gov.br")
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build();
        return factory.createClient(IbgeCountryClient.class);
    }
}
