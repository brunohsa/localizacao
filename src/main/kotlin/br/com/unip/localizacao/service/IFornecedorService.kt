package br.com.unip.localizacao.service

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.dto.FiltroFornecedorDTO
import br.com.unip.localizacao.dto.FornecedorEncontradoDTO

interface IFornecedorService {

    fun buscar(filtro: FiltroFornecedorDTO) : List<FornecedorEncontradoDTO>

    fun buscarCadostrosUUID(coordenadas: CoordenadasDTO): List<String>
}