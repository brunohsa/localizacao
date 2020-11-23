package br.com.unip.localizacao.repository

import br.com.unip.localizacao.dto.CoordenadasDTO

interface IGoogleMapsRepository {

    fun buscarCoordenadasPorCep(cep: String): CoordenadasDTO?
}