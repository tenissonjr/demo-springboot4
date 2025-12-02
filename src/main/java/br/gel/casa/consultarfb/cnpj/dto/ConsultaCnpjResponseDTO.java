package br.gel.casa.consultarfb.cnpj.dto;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import br.gel.casa.consultarfb.cnpj.dto.ConsultaDataPrevCnpjResponseDTO.Estabelecimento;
import br.gel.casa.consultarfb.cnpj.views.CnpjViews;
import br.gel.casa.consultarfb.infraestructure.exception.ApplicationException;

public record ConsultaCnpjResponseDTO(
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


    public static ConsultaCnpjResponseDTO valueOf(ConsultaDataPrevCnpjResponseDTO bcnpjResponseDTO) {
		String cnpj =  bcnpjResponseDTO.estabelecimentos().keySet().stream().findFirst().orElseThrow(() -> new ApplicationException("Estabelecimento não encontrado"));
        Estabelecimento estabelecimento = bcnpjResponseDTO.estabelecimentos().values().stream().findFirst().orElseThrow(()->new ApplicationException("Estabelecimento não encontrado"));

        return new ConsultaCnpjResponseDTO(
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
			bcnpjResponseDTO.simplesMei() != null ? bcnpjResponseDTO.simplesMei().mei() : Collections.emptyList()	,
			//Simples Nacional
			bcnpjResponseDTO.simplesMei() != null ? bcnpjResponseDTO.simplesMei().simples() : Collections.emptyList()

		);
    }
}
