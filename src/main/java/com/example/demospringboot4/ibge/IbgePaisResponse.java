package com.example.demospringboot4.ibge;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IbgePaisResponse(Id id, String nome) {

    public record Id(int M49,@JsonProperty("ISO-ALPHA-2") String ISO_ALPHA_2,@JsonProperty("ISO-ALPHA-3") String ISO_ALPHA_3) {}

}
