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
        return groups -> {
            groups.filterByName("b-cnpj").forEachClient((group, clientBuilder) -> clientBuilder
                    .baseUrl(bcnpjBaseUrl)
                    .defaultHeader(HttpHeaders.AUTHORIZATION, encodeBasic(bcnpjUsername, bcnpjPassword)).build());
        };
    }
}
