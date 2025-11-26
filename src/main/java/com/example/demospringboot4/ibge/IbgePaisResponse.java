package com.example.demospringboot4.ibge;

public record IbgePaisResponse(Id id, String nome) {

    public record Id(int M49, String ISO_ALPHA_2, String ISO_ALPHA_3) {}

}
