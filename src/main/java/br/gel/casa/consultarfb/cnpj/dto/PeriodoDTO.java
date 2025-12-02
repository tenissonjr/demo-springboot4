package br.gel.casa.consultarfb.cnpj.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;

import br.gel.casa.consultarfb.cnpj.views.CnpjViews;

import com.fasterxml.jackson.annotation.JsonFormat;
import tools.jackson.databind.annotation.JsonDeserialize;


public record PeriodoDTO(
    @JsonView(CnpjViews.Completo.class)
    @JsonDeserialize(using = DataPeriodoSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate dataInicio,

    @JsonView(CnpjViews.Completo.class)
    @JsonDeserialize(using = DataPeriodoSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate dataFim){}