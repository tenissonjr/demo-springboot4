package br.gel.casa.consultarfb.cpf.dto;

/**
 * Record que mapeia a estrutura do JSON fornecido.
 */
public record ConsultaDataPrevCpfResponseDTO(
        String cpf,
        String nome,
        String situacaoCadastral,
        String residenteExterior,
        String codigoPaisExterior,
        String nomePaisExterior,
        String nomeMae,
        String dataNascimento,
        String sexo,
        String naturezaOcupacao,
        String ocupacaoPrincipal,
        String exercicioOcupacao,
        String tipoLogradouro,
        String logradouro,
        String numeroLogradouro,
        String complemento,
        String bairro,
        String cep,
        String uf,
        String codigoMunicipio,
        String municipio,
        String ddd,
        String telefone,
        String anoObito,
        String estrangeiro,
        String dataAtualizacao,
        String tituloEleitor,
        String erro,
        String codigoUnidadeAdministrativa,
        String nomeUnidadeAdministrativa,
        String ddi,
        String codigoPaisNacionalidade,
        String nomePaisNacionalidade,
        String codigoMunicipioNaturalidade,
        String nomeMunicipioNaturalidade,
        String ufMunicipioNaturalidade,
        String dataInscricao,
        String nomeSocial) {

}
