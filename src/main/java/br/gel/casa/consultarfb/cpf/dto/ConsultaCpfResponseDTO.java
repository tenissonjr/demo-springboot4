package br.gel.casa.consultarfb.cpf.dto;

import com.fasterxml.jackson.annotation.JsonView;

import br.gel.casa.consultarfb.cpf.views.CpfViews;

/**
 * Record que mapeia os atributos de CpfCompletoDTO.
 */
public record ConsultaCpfResponseDTO(
    @JsonView(CpfViews.Basico.class) String codigoConsulta,
    @JsonView(CpfViews.Basico.class) String cpf,
    @JsonView(CpfViews.Basico.class) String nome,
    @JsonView(CpfViews.Basico.class) String nomeSocial,
    @JsonView(CpfViews.Basico.class) String nomeMae,
    @JsonView(CpfViews.Basico.class) String dataNascimento,
    @JsonView(CpfViews.Basico.class) String situacaoCadastral,

    @JsonView(CpfViews.Complemento.class) String ddi,
    @JsonView(CpfViews.Complemento.class) String ddd,
    @JsonView(CpfViews.Complemento.class) String telefone,

    @JsonView(CpfViews.Completo.class) String estrangeiro,
    @JsonView(CpfViews.Completo.class) String residenteExterior,
    @JsonView(CpfViews.Completo.class) String codigoPaisExterior,
    @JsonView(CpfViews.Completo.class) String nomePaisExterior,

    @JsonView(CpfViews.Completo.class) String naturezaOcupacao,
    @JsonView(CpfViews.Completo.class) String ocupacaoPrincipal,
    @JsonView(CpfViews.Completo.class) String exercicioOcupacao,

    @JsonView(CpfViews.Completo.class) String tipoLogradouro,
    @JsonView(CpfViews.Completo.class) String logradouro,
    @JsonView(CpfViews.Completo.class) String numeroLogradouro,
    @JsonView(CpfViews.Completo.class) String complemento,
    @JsonView(CpfViews.Completo.class) String bairro,
    @JsonView(CpfViews.Completo.class) String cep,
    @JsonView(CpfViews.Completo.class) String codigoMunicipio,
    @JsonView(CpfViews.Completo.class) String municipio,
    @JsonView(CpfViews.Completo.class) String uf,

    @JsonView(CpfViews.Completo.class) String codigoUnidadeAdministrativa,
    @JsonView(CpfViews.Completo.class) String nomeUnidadeAdministrativa,
    @JsonView(CpfViews.Completo.class) String codigoPaisNacionalidade,
    @JsonView(CpfViews.Completo.class) String nomePaisNacionalidade
){

    public static ConsultaCpfResponseDTO valueOf(ConsultaDataPrevCpfResponseDTO consultarCpfDataPrev,String codigoConsulta) {
        return new ConsultaCpfResponseDTO(
			codigoConsulta,
			consultarCpfDataPrev.cpf(),
			consultarCpfDataPrev.nome(),
			consultarCpfDataPrev.nomeSocial(),
			consultarCpfDataPrev.nomeMae(),
			consultarCpfDataPrev.dataNascimento(),
			consultarCpfDataPrev.situacaoCadastral(),

			consultarCpfDataPrev.ddi(),
			consultarCpfDataPrev.ddd(),
			consultarCpfDataPrev.telefone(),

			consultarCpfDataPrev.estrangeiro(),
			consultarCpfDataPrev.residenteExterior(),
			consultarCpfDataPrev.codigoPaisExterior(),
			consultarCpfDataPrev.nomePaisExterior(),

			consultarCpfDataPrev.naturezaOcupacao(),
			consultarCpfDataPrev.ocupacaoPrincipal(),
			consultarCpfDataPrev.exercicioOcupacao(),

			consultarCpfDataPrev.tipoLogradouro(),
			consultarCpfDataPrev.logradouro(),
			consultarCpfDataPrev.numeroLogradouro(),
			consultarCpfDataPrev.complemento(),
			consultarCpfDataPrev.bairro(),
			consultarCpfDataPrev.cep(),
			consultarCpfDataPrev.codigoMunicipio(),
			consultarCpfDataPrev.municipio(),
			consultarCpfDataPrev.uf(),

			consultarCpfDataPrev.codigoUnidadeAdministrativa(),
			consultarCpfDataPrev.nomeUnidadeAdministrativa(),
			consultarCpfDataPrev.codigoPaisNacionalidade(),
			consultarCpfDataPrev.nomePaisNacionalidade()

		);
    }
}