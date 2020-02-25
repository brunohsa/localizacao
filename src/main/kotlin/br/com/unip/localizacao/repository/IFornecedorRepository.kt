package br.com.unip.localizacao.repository

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.dto.FornecedorEncontradoDTO

interface IFornecedorRepository {

    fun buscar(coordenadas : CoordenadasDTO) : List<FornecedorEncontradoDTO>
}