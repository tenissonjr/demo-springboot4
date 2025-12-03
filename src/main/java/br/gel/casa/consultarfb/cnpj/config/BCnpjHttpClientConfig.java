package br.gel.casa.consultarfb.cnpj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import static br.gel.casa.consultarfb.infraestructure.utils.EncodeUtil.encodeBasic;
import br.gel.casa.consultarfb.cnpj.interfaces.IDataPrevCnpjHttpClient;

@Configuration
@Profile("prd")
class BCnpjHttpClientConfig {

    @Value("${bcnpj.base.url}")
    private String bcnpjBaseUrl;

    @Value("${bcnpj.credentials.username}")
    private String bcnpjUsername;

    @Value("${bcnpj.credentials.password}")
    private String bcnpjPassword;

    @Bean
    public IDataPrevCnpjHttpClient dataPrevCnpjHttpClient() {

        // Configura o RestClient com a URL base da API do BCnpj
        var restClient = RestClient.builder()
                .baseUrl(bcnpjBaseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, encodeBasic(bcnpjUsername, bcnpjPassword))
                .build();

        // Retorna um objeto criado pelo Spring Boot que implementa a interface IBCnpjService
        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(restClient))
                .build()
                .createClient(IDataPrevCnpjHttpClient.class);
    }

}

/* 
package br.gel.casa.consultarfb.cnpj.config;

import static br.gel.casa.consultarfb.infraestructure.utils.EncodeUtil.encodeBasic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

import br.gel.casa.consultarfb.cnpj.interfaces.IDataPrevCnpjHttpClient;

@Configuration
@Profile("prd")
@ImportHttpServices(group = "b-cnpj", types = { IDataPrevCnpjHttpClient.class })
class BCnpjHttpClientConfig {

    @Value("${bcnpj.base.url}")
    private String bcnpjBaseUrl;

    @Value("${bcnpj.credentials.username}")
    private String bcnpjUsername;

    @Value("${bcnpj.credentials.password}")
    private String bcnpjPassword;

    @Bean
    public RestClientHttpServiceGroupConfigurer groupConfigurer() {
        //Configurar application/json se necessario 
        return groups -> {
            groups.forEachClient((group, clientBuilder) -> clientBuilder
                    .baseUrl(bcnpjBaseUrl)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .defaultHeader(HttpHeaders.AUTHORIZATION, encodeBasic(bcnpjUsername, bcnpjPassword)).build());
        };
    }
}
*/