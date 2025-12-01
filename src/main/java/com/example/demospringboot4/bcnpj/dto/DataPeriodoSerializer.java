package com.example.demospringboot4.bcnpj.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

class DataPeriodoSerializer extends StdDeserializer<LocalDate> {

   private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String ZERO_DATE = "00000000";

    public DataPeriodoSerializer() { 
        super(LocalDate.class); 
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        String dateStr = p.getString();
        if ( StringUtils.isBlank(dateStr) ||  ZERO_DATE.equals(dateStr)) {
            return null;
        }
        
        return dateStr.length() == 8 
            ? LocalDate.parse(dateStr, FORMATTER)
            : LocalDate.parse(dateStr);
    }

}
