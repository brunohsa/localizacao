package br.com.unip.localizacao.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class FiltroFornecedorDTO(val notaApartirDe: Int?,
                               val notaMenorQue: Int?,
                               val nome: String?,
                               val categoria: String?,
                               val tipoOrdenacao: String?,
                               val campoOrdenacao: String?,
                               val limite: Int?,
                               val coordenadas: CoordenadasDTO)