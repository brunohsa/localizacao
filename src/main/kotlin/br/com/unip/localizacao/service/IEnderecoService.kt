package br.com.unip.localizacao.service

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.dto.EnderecoDTO

interface IEnderecoService {

    fun buscarCoordenadasPorCep(cep: String): CoordenadasDTO

    fun buscarPorCep(cep: String): EnderecoDTO
}