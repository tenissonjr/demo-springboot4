package br.gel.casa.consultarfb.cpf.config;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import br.gel.casa.consultarfb.cpf.interfaces.IDataPrevCpfHttpClient;

@Configuration
class BCpfHttpClientConfig {

    @Value("${bcpf.base.url}")
    private String bcpfBaseUrl;

    @Value("${bcpf.credentials.username}")
    private String username;

    @Value("${bcpf.credentials.password}")
    private String password;

    private String encodeBasic(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }


    @Profile("prd")
    @Bean
    public IDataPrevCpfHttpClient dataPrevCpfHttpClient() {

        // Configura o RestClient com a URL base da API do BCpf
        var restClient = RestClient.builder()
                .baseUrl(bcpfBaseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, encodeBasic(username, password))
                .build();

        // Retorna um objeto criado pelo Spring Boot que implementa a interface IDataPrevCpfHttpClient
        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(restClient))
                .build()
                .createClient(IDataPrevCpfHttpClient.class);
    }

}
