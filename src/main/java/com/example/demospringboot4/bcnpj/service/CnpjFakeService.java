package com.example.demospringboot4.bcnpj.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.example.demospringboot4.bcnpj.dto.ConsultaCnpjDataPrevResponseDTO;
import com.example.demospringboot4.bcnpj.dto.ConsultaCnpjResponseDTO;
import com.example.demospringboot4.bcnpj.interfaces.ICnpjService;
import com.example.demospringboot4.infraestructure.exception.ApplicationEntityNotFound;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

@Profile("dev")
@Component
public class CnpjFakeService implements CommandLineRunner, ICnpjService {

    private static final Logger log = LoggerFactory.getLogger(CnpjFakeService.class);
    private static final String CNPJS_JSON_PATH = "classpath:data/cnpj-fake-list.json";

    private final JsonMapper jsonMapper;
    private final ResourceLoader resourceLoader;
    private List<ConsultaCnpjDataPrevResponseDTO> cnpjsFake;

    public CnpjFakeService(JsonMapper jsonMapper, ResourceLoader resourceLoader) {
        this.jsonMapper = jsonMapper;
        this.resourceLoader = resourceLoader;
    }

    public List<ConsultaCnpjDataPrevResponseDTO> getCnpjsFake() {
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

            cnpjsFake.forEach(System.out::println);

            // Demonstrate serialization back to JSON (using the configured JsonMapper)
            //validateSerialization(cnpjsFake);

        } catch (JacksonException e) {
            // Jackson 3: All exceptions extend JacksonException (RuntimeException)
            // This is an unchecked exception, making it easier to use in lambdas
            log.error("Falha ao carregar dados de CNPJ do arquivo JSON: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            // Handle other potential exceptions (e.g., resource loading)
            log.error("Erro inesperado ao carregar dados de CNPJ", e);
            throw e;
        }
    }

    private void validateSerialization(List<ConsultaCnpjDataPrevResponseDTO> donuts) {
        log.info("Serializing CNPJs \uD83C\uDF69");

        if(!donuts.isEmpty()) {
            try {
                // Get the first donut and serialize it
                String json = jsonMapper.writeValueAsString(donuts.getFirst());

                // NOTE: How are properties in the JSON sorted?
                log.info("\n{}", json);

            } catch (JacksonException e) {
                // Jackson 3: Unchecked exception for serialization errors
                log.error("Falha ao serializar CNPJ para JSON: {}", e.getMessage(), e);
            }
        }
    }

    @Override
    public ConsultaCnpjResponseDTO consultarCnpj(String cnpj) {
        if(this.cnpjsFake != null) {
            return this.cnpjsFake.stream()
                    .filter(c -> c.estabelecimentos().containsKey(cnpj))
                    .findFirst()
                    .map(ConsultaCnpjResponseDTO::valueOf)
                    .orElseThrow(() -> new ApplicationEntityNotFound("CNPJ não encontrado: " + cnpj));
        }
        throw new ApplicationEntityNotFound("Nenhum CNPJ carregado na memória.");
    }


}
