package com.example.demospringboot4.bcnpj.dto;

import java.util.List;
import java.util.Map;

/**
 * Record que mapeia a estrutura do JSON fornecido pelo .
 */
public record ConsultaDataPrevCnpjResponseDTO(
    Empresa empresa
    ,Map<String, Estabelecimento> estabelecimentos,
    Map<String, Socio> socios,
    SimplesMei simplesMei
) {
    public record Empresa(
        String porteEmpresa,
        String capitalSocial,
        String cpfResponsavel,
        String nomeEmpresarial,
        String naturezaJuridica,
        String qualificacaoResponsavel
    ) {}

    public record Estabelecimento(
        String uf,
        String cep,
        String ddd1,
        String ddd2,
        String pais,
        String email,
        String bairro,
        String numero,
        String municipio,
        String telefone1,
        String telefone2,
        String cnaeFiscal,
        String logradouro,
        String complemento,
        String dataCadastro,
        String nomeFantasia,
        String cidadeExterior,
        String tipoLogradouro,
        List<String> cnaesSecundarias,
        String situacaoEspecial,
        String situacaoCadastral,
        String dataSituacaoEspecial,
        String dataSituacaoCadastral,
        String motivoSituacaoCadastral,
        String identificadorMatrizFilial
    ) {}

    public record Socio(
        String pais,
        String entradaSociedade,
        String socioEstrangeiro,
        String qualificacaoSocio,
        String identificadorSocio,
        String cpfRepresentanteLegal,
        String qualificacaoRepresentanteLegal
    ) {}

    public record SimplesMei(
        List<PeriodoDTO> mei,
        List<PeriodoDTO> simples
    ) {
       
    }
}