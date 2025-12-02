package br.gel.casa.consultarfb.cnpj.dto;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

public class NullValueAsEmptyListDeserializer extends StdDeserializer<List<PeriodoDTO>> {

    public NullValueAsEmptyListDeserializer() {
        super(List.class);
    }
    
    @Override
    public List<PeriodoDTO> getNullValue(DeserializationContext ctxt) {
        return Collections.emptyList();
    }

    @Override
    public List<PeriodoDTO> deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
          return ctxt.readValue(p, ctxt.getTypeFactory().constructCollectionType(List.class, Object.class));
    }

}
