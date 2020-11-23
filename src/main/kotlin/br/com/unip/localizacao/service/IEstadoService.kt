package br.com.unip.localizacao.service

import br.com.unip.localizacao.dto.EstadoDTO

interface IEstadoService {

    fun buscar(): List<EstadoDTO>
}