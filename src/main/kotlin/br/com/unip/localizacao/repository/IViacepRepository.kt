package br.com.unip.localizacao.repository

import br.com.unip.localizacao.dto.EnderecoDTO

interface IViacepRepository {

    fun buscarEnderecoPorCEP(cep: String): EnderecoDTO?
}