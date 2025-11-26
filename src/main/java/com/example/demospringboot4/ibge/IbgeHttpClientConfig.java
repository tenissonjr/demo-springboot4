package com.example.demospringboot4.ibge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class IbgeHttpClientConfig {

    @Bean
    RestClient jsonplaceholderRestClient() {
        return RestClient.builder()
                .baseUrl("https://servicodados.ibge.gov.br")
                .build();
    }    

    @Bean
    HttpServiceProxyFactory jsonPlaceholderProxyFactory(RestClient jsonplaceholderRestClient) {
        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(jsonplaceholderRestClient))
                .build();
    }

   @Bean
    IbgeLocalidadesService ibgeLocalidadesService(HttpServiceProxyFactory jsonPlaceholderProxyFactory) {
        return jsonPlaceholderProxyFactory.createClient(IbgeLocalidadesService.class);
    }


}
