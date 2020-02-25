package br.com.unip.localizacao.repository

import br.com.unip.localizacao.dto.EnderecoDTO

interface IGoogleMapsRepository {

    fun buscarEnderecoPorCep(cep: String): EnderecoDTO
}