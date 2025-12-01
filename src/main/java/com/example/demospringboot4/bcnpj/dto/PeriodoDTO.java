package com.example.demospringboot4.bcnpj.dto;

import java.time.LocalDate;

import com.example.demospringboot4.bcnpj.views.CnpjViews;
import com.fasterxml.jackson.annotation.JsonView;
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