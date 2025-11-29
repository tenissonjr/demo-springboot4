package com.example.demospringboot4.bcnpj.config;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.example.demospringboot4.bcnpj.dto.ConsultaDataPrevCnpjResponseDTO;
import com.example.demospringboot4.bcnpj.interfaces.IDataPrevCnpjHttpClient;
import com.example.demospringboot4.infraestructure.exception.ApplicationResourceNotFound;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

@Profile("dev")
@Component
public class DataPrevCnpjHttpFakeClient implements CommandLineRunner, IDataPrevCnpjHttpClient {

    private static final Logger log = LoggerFactory.getLogger(DataPrevCnpjHttpFakeClient.class);
    private static final String CNPJS_JSON_PATH = "classpath:data/cnpj-fake-list.json";

    private final JsonMapper jsonMapper;
    private final ResourceLoader resourceLoader;
    private List<ConsultaDataPrevCnpjResponseDTO> cnpjsFake;

    public DataPrevCnpjHttpFakeClient(JsonMapper jsonMapper, ResourceLoader resourceLoader) {
        this.jsonMapper = jsonMapper;
        this.resourceLoader = resourceLoader;
    }

    public List<ConsultaDataPrevCnpjResponseDTO> getCnpjsFake() {
        return cnpjsFake;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Carregando CNPJs \uD83C\uDF69");

        try {
            Resource resource = resourceLoader.getResource(CNPJS_JSON_PATH);

            if(!resource.exists()) {
                log.error("Arquivo CNPJ não encontrado em: {}", CNPJS_JSON_PATH);
                return;
            }

            this.cnpjsFake = jsonMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<>() {}
            );
            //Informar quantos CNPJs foram carregados
            log.info("Foram carregados {} CNPJs fakes na memória.", cnpjsFake.size());
            cnpjsFake.forEach(cnpj -> 
                cnpj.estabelecimentos().keySet().forEach(System.out::println)
            );

        } catch (JacksonException e) {
            // Jackson 3: All exceptions extend JacksonException (RuntimeException)
            // This is an unchecked exception, making it easier to use in lambdas
            log.error("Falha ao carregar dados de CNPJ do arquivo JSON: {}", e.getMessage(), e);
            throw e;
        } catch (IOException e) {
            // Handle other potential exceptions (e.g., resource loading)
            log.error("Erro inesperado ao carregar dados de CNPJ", e);
            throw e;
        }
    }


    @Override
    public ConsultaDataPrevCnpjResponseDTO consultarCnpjDataPrev(String cnpj) {
        if(this.cnpjsFake != null) {
            return this.cnpjsFake.stream()
                    .filter(c -> c.estabelecimentos().containsKey(cnpj))
                    .findFirst()
                    .orElseThrow(() -> new ApplicationResourceNotFound("CNPJ não encontrado: " + cnpj));
        }
        throw new ApplicationResourceNotFound("Nenhum CNPJ carregado na memória.");
    }


}
