package br.gel.casa.consultarfb.cnpj.dto;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class EmptyStringAsEmptyListDeserializer extends StdDeserializer<List<String>> {


    public EmptyStringAsEmptyListDeserializer() {
        super(List.class);
    }

    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException  {

        String listStr = p.getString();
        if ( StringUtils.isBlank(listStr) ) {
            return Collections.emptyList();
        }
        
        return ctxt.readValue(p, ctxt.getTypeFactory().constructCollectionType(List.class, String.class));
 
    }

    @Override
    public List<String> getNullValue(DeserializationContext ctxt) {
        return Collections.emptyList();
    }

}
