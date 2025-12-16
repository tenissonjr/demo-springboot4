package br.gel.casa.consultarfb.cpf.config;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import br.gel.casa.consultarfb.cpf.dto.ConsultaDataPrevCpfResponseDTO;
import br.gel.casa.consultarfb.cpf.interfaces.IDataPrevCpfHttpClient;
import br.gel.casa.consultarfb.infraestructure.exception.ApplicationEntityNotFound;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

@Profile("dev")
@Component
public class DataPrevCpfHttpFakeClient implements CommandLineRunner, IDataPrevCpfHttpClient {

    private static final Logger log = LoggerFactory.getLogger(DataPrevCpfHttpFakeClient.class);
    private static final String CPFS_JSON_PATH = "classpath:data/cpf-fake-list.json";

    private final JsonMapper jsonMapper;
    private final ResourceLoader resourceLoader;
    private List<ConsultaDataPrevCpfResponseDTO> cpfsFake;


    public DataPrevCpfHttpFakeClient(JsonMapper jsonMapper, ResourceLoader resourceLoader) {
        this.jsonMapper = jsonMapper;
        this.resourceLoader = resourceLoader;
    }

    public List<ConsultaDataPrevCpfResponseDTO> getCpfsFake() {
        return cpfsFake;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Carregando CPFs \uD83C\uDF69");

        try {
            Resource resource = resourceLoader.getResource(CPFS_JSON_PATH);

            if(!resource.exists()) {
                log.error("Arquivo CPF n\u00e3o encontrado em: {}", CPFS_JSON_PATH);
                return;
            }

            this.cpfsFake = jsonMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<>() {}
            );
            //Informar quantos CPFs foram carregados
            log.info("Foram carregados {} CPFs fakes na mem\u00f3ria.", cpfsFake.size());
            //Lista as propriedades cpf carregadas da lista
            cpfsFake.forEach(item -> 
                log.info(item.cpf())
            );
            
            

        } catch (JacksonException e) {
            // Jackson 3: All exceptions extend JacksonException (RuntimeException)
            // This is an unchecked exception, making it easier to use in lambdas
            log.error("Falha ao carregar dados de CPF do arquivo JSON: {}", e.getMessage(), e);
            throw e;
        } catch (IOException e) {
            // Handle other potential exceptions (e.g., resource loading)
            log.error("Erro inesperado ao carregar dados de CPF", e);
            throw e;
        }
    }


    @Override
    public ConsultaDataPrevCpfResponseDTO consultarCpfDataPrev(String cpfParam) {
        if(this.cpfsFake != null) {
            return this.cpfsFake.stream()
                    .filter(c -> c.cpf().equals(cpfParam))
                    .findFirst()
                    .orElseThrow(() -> new ApplicationEntityNotFound("CPF não encontrado: " + cpfParam));
        }
        throw new ApplicationEntityNotFound("Nenhum CPF carregado na memória.");
    }


}
