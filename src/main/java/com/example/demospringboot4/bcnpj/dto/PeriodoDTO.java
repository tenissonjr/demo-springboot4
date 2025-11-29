package com.example.demospringboot4.bcnpj.dto;

import com.example.demospringboot4.bcnpj.views.CnpjViews;
import com.fasterxml.jackson.annotation.JsonView;

public record PeriodoDTO(
           @JsonView(CnpjViews.Completo.class)     String dataFim,
           @JsonView(CnpjViews.Completo.class)     String dataInicio) {
}