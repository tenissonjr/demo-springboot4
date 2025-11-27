package com.example.demospringboot4.bcnpj.dto;

import java.util.List;

import com.example.demospringboot4.bcnpj.dto.BCnpjResponseDTO.Estabelecimento;

public record ConsultaRfbCnpjResponseDTO(
	//Empresa
    String cnpj,
    String nomeEmpresarial,
    String naturezaJuridica,
    String qualificacaoResponsavel,
    String porteEmpresa,
    String capitalSocial,
	//Estabelecimento
    String nomeFantasia,
    String situacaoCadastral,
    String cnaeFiscal,
    List<String> cnaesSecundarias,
    String logradouro,
    String bairro,
    String uf,
    String cep,
    String municipio,
    String pais,
    String ddd,
	//Mei
    List<PeriodoDTO> mei,
	//Simples Nacional
    List<PeriodoDTO> simplesNacional
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
