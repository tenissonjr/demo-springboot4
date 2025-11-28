package com.example.demospringboot4.bcnpj.dto;

import java.util.List;

import com.example.demospringboot4.bcnpj.dto.BCnpjResponseDTO.Estabelecimento;
import com.example.demospringboot4.bcnpj.views.CnpjViews;
import com.fasterxml.jackson.annotation.JsonView;

public record ConsultaRfbCnpjResponseDTO(
	//Empresa
     @JsonView(CnpjViews.Basico.class) String cnpj,
     @JsonView(CnpjViews.Basico.class) String nomeEmpresarial,
     @JsonView(CnpjViews.Basico.class) String naturezaJuridica,
     @JsonView(CnpjViews.Basico.class) String qualificacaoResponsavel,
     @JsonView(CnpjViews.Basico.class) String porteEmpresa,
     @JsonView(CnpjViews.Basico.class)String capitalSocial,
	//Estabelecimento
     @JsonView(CnpjViews.Basico.class) String nomeFantasia,
     @JsonView(CnpjViews.Basico.class) String situacaoCadastral,
     @JsonView(CnpjViews.Basico.class) String cnaeFiscal,

     @JsonView(CnpjViews.Completo.class) List<String> cnaesSecundarias,
     @JsonView(CnpjViews.Completo.class) String logradouro,
     @JsonView(CnpjViews.Completo.class) String bairro,
     @JsonView(CnpjViews.Completo.class)String uf,
     @JsonView(CnpjViews.Completo.class)String cep,
     @JsonView(CnpjViews.Completo.class)String municipio,
     @JsonView(CnpjViews.Completo.class)String pais,
     @JsonView(CnpjViews.Completo.class)String ddd,
	//Mei
     @JsonView(CnpjViews.Completo.class)List<PeriodoDTO> mei,
	//Simples Nacional
     @JsonView(CnpjViews.Completo.class)List<PeriodoDTO> simplesNacional
) {


    public static ConsultaRfbCnpjResponseDTO valueOf(BCnpjResponseDTO bcnpjResponseDTO) {
		String cnpj =  bcnpjResponseDTO.estabelecimentos().keySet().stream().findFirst().orElseThrow(() -> new IllegalArgumentException("Estabelecimento não encontrado"));
        Estabelecimento estabelecimento = bcnpjResponseDTO.estabelecimentos().values().stream().findFirst().orElseThrow(()->new IllegalArgumentException("Estabelecimento não encontrado"));

        return new ConsultaRfbCnpjResponseDTO(
			//Empresa
			cnpj,
			bcnpjResponseDTO.empresa().nomeEmpresarial(),
			bcnpjResponseDTO.empresa().naturezaJuridica(),
			bcnpjResponseDTO.empresa().qualificacaoResponsavel(),
			bcnpjResponseDTO.empresa().porteEmpresa(),
			bcnpjResponseDTO.empresa().capitalSocial(),
			//Estabelecimento
			estabelecimento.nomeFantasia(),
			estabelecimento.situacaoCadastral(),
			estabelecimento.cnaeFiscal(),
			estabelecimento.cnaesSecundarias(),
			estabelecimento.logradouro(),
			estabelecimento.bairro(),
			estabelecimento.uf(),
			estabelecimento.cep(),
			estabelecimento.municipio(),
			estabelecimento.pais(),
			estabelecimento.ddd1(),
			//Mei
			bcnpjResponseDTO.simplesMei() != null ? bcnpjResponseDTO.simplesMei().mei() : null,
			//Simples Nacional
			bcnpjResponseDTO.simplesMei() != null ? bcnpjResponseDTO.simplesMei().simples() : null

		);
    }
}
