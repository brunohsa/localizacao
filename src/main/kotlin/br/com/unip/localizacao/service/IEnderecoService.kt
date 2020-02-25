package br.com.unip.localizacao.service

import br.com.unip.localizacao.dto.EnderecoDTO

interface IEnderecoService {

    fun buscarPorCep(cep: String?): EnderecoDTO
}