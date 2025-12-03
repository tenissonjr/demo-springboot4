package br.gel.casa.consultarfb.cpf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

import br.gel.casa.consultarfb.cpf.interfaces.IDataPrevCpfHttpClient;
import static br.gel.casa.consultarfb.infraestructure.utils.EncodeUtil.encodeBasic;

@Configuration
@Profile("prd")
@ImportHttpServices(group = "b-cpf"  , types = {IDataPrevCpfHttpClient.class})
class BCpfHttpClientConfig {

    @Value("${bcpf.base.url}")
    private String bcpfBaseUrl;

    @Value("${bcpf.credentials.username}")
    private String bcpfUsername;

    @Value("${bcpf.credentials.password}")
    private String bcpfPassword;


	@Bean
	public RestClientHttpServiceGroupConfigurer dataPrevCpfHttpClientConfigurer() {
		return groups -> {
			groups.forEachClient((group, clientBuilder) ->
					clientBuilder
                    .baseUrl(bcpfBaseUrl)
                    .defaultHeader(HttpHeaders.AUTHORIZATION, encodeBasic(bcpfUsername, bcpfPassword)).build());
		};
	}
}

