package br.com.unip.localizacao.repository

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.dto.FiltroFornecedorDTO
import br.com.unip.localizacao.dto.FornecedorEncontradoDTO

interface IFornecedorRepository {

    fun buscar(filtro: FiltroFornecedorDTO) : List<FornecedorEncontradoDTO>

    fun buscarCadastrosUUID(coordenadas: CoordenadasDTO): List<String>
}